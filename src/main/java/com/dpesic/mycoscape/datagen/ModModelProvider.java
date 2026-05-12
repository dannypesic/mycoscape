package com.dpesic.mycoscape.datagen;

import com.dpesic.mycoscape.block.AbstractFungusBlock;
import com.dpesic.mycoscape.core.ModBlocks;
import com.dpesic.mycoscape.core.ModItems;
import com.dpesic.mycoscape.core.Mycoscape;
import com.google.gson.JsonObject;
import com.mojang.math.Quadrant;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.ConditionBuilder;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.model.BlockModelDefinition;
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.client.renderer.block.model.VariantMutator;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.core.Direction;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.state.properties.StairsShape;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class ModModelProvider implements DataProvider {


    private static final VariantMutator UV_LOCK     = VariantMutator.UV_LOCK.withValue(true);
    private static final VariantMutator NOP          = v -> v;
    private static final VariantMutator Y_ROT_90    = VariantMutator.Y_ROT.withValue(Quadrant.R90);
    private static final VariantMutator Y_ROT_180   = VariantMutator.Y_ROT.withValue(Quadrant.R180);
    private static final VariantMutator Y_ROT_270   = VariantMutator.Y_ROT.withValue(Quadrant.R270);
    private static final VariantMutator X_ROT_90    = VariantMutator.X_ROT.withValue(Quadrant.R90);
    private static final VariantMutator X_ROT_180   = VariantMutator.X_ROT.withValue(Quadrant.R180);

    private static final PropertyDispatch<VariantMutator> ROTATION_HORIZONTAL_FACING_ALT =
        PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING)
            .select(Direction.SOUTH, NOP)
            .select(Direction.WEST,  Y_ROT_90)
            .select(Direction.NORTH, Y_ROT_180)
            .select(Direction.EAST,  Y_ROT_270);

    private final PackOutput.PathProvider blockStatePath;
    private final PackOutput.PathProvider modelPath;
    private final PackOutput.PathProvider itemPath;

    public ModModelProvider(PackOutput output) {
        this.blockStatePath = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "blockstates");
        this.modelPath = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models");
        this.itemPath = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "items");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        Map<Block, BlockModelDefinitionGenerator> blockStateGenerators = new LinkedHashMap<>();
        Map<Identifier, ModelInstance> models = new LinkedHashMap<>();
        Map<Item, ClientItem> items = new LinkedHashMap<>();

        Consumer<BlockModelDefinitionGenerator> bsOutput = gen -> blockStateGenerators.put(gen.block(), gen);
        BiConsumer<Identifier, ModelInstance> modelOutput = models::put;
        ItemModelOutput itemOutput = new ItemModelOutput() {
            @Override
            public void accept(Item item, ItemModel.Unbaked model, ClientItem.Properties props) {
                items.put(item, new ClientItem(model, props));
            }
            @Override
            public void copy(Item donor, Item acceptor) {}
        };

        BlockModelGenerators bmg = new BlockModelGenerators(bsOutput, itemOutput, modelOutput);
        generateBlocks(bmg, bsOutput, modelOutput, itemOutput);
        generateItems(itemOutput, modelOutput);

        Map<Block, BlockModelDefinition> definitions = new LinkedHashMap<>();
        blockStateGenerators.forEach((block, gen) -> definitions.put(block, gen.create()));

        Function<Block, java.nio.file.Path> bsPathGetter =
            b -> blockStatePath.json(b.builtInRegistryHolder().key().identifier());
        Function<Item, java.nio.file.Path> itemPathGetter =
            i -> itemPath.json(i.builtInRegistryHolder().key().identifier());

        return CompletableFuture.allOf(
            DataProvider.saveAll(cache, BlockModelDefinition.CODEC, bsPathGetter, definitions),
            DataProvider.saveAll(cache, mi -> mi.get(), modelPath::json, models),
            DataProvider.saveAll(cache, ClientItem.CODEC, itemPathGetter, items)
        );
    }

    private void generateBlocks(BlockModelGenerators bmg,
                                 Consumer<BlockModelDefinitionGenerator> bsOutput,
                                 BiConsumer<Identifier, ModelInstance> modelOutput,
                                 ItemModelOutput itemOutput) {

        overgrownGrassBlock(ModBlocks.OVERGROWN_GRASS.get(), bsOutput, modelOutput);
        bmg.createTrivialCube(ModBlocks.JACK_O_LANTERN_VEIN.get());
        bmg.createTrivialCube(ModBlocks.MYCOSLATE.get());
        bmg.createTrivialCube(ModBlocks.ROTWOOD_PLANKS.get());


        leavesBlock(ModBlocks.ROTWOOD_LEAVES.get(), bsOutput, modelOutput, itemOutput);


        bmg.createAxisAlignedPillarBlock(ModBlocks.ROTWOOD.get(), TexturedModel.COLUMN);
        bmg.createAxisAlignedPillarBlock(ModBlocks.STRIPPED_ROTWOOD_LOG.get(), TexturedModel.COLUMN);
        bmg.createAxisAlignedPillarBlock(ModBlocks.ROTWOOD_WOOD.get(), TexturedModel.COLUMN);
        bmg.createAxisAlignedPillarBlock(ModBlocks.STRIPPED_ROTWOOD_WOOD.get(), TexturedModel.COLUMN);


        TextureMapping planksMapping = TextureMapping.cube(ModBlocks.ROTWOOD_PLANKS.get());
        rotwoodStairs(ModBlocks.ROTWOOD_STAIRS.get(), planksMapping, bsOutput, modelOutput);
        rotwoodSlab(ModBlocks.ROTWOOD_SLAB.get(), planksMapping, bsOutput, modelOutput);
        rotwoodFence(ModBlocks.ROTWOOD_FENCE.get(), planksMapping, bsOutput, modelOutput, itemOutput);
        rotwoodFenceGate(ModBlocks.ROTWOOD_FENCE_GATE.get(), planksMapping, bsOutput, modelOutput);
        rotwoodDoor(ModBlocks.ROTWOOD_DOOR.get(), bsOutput, modelOutput);
        rotwoodTrapdoor(ModBlocks.ROTWOOD_TRAPDOOR.get(), bsOutput, modelOutput, itemOutput);
        rotwoodButton(ModBlocks.ROTWOOD_BUTTON.get(), planksMapping, bsOutput, modelOutput, itemOutput);
        rotwoodPressurePlate(ModBlocks.ROTWOOD_PRESSURE_PLATE.get(), planksMapping, bsOutput, modelOutput);


        bmg.createTrivialCube(ModBlocks.BLEWIT_MUSHROOM_CAP.get());
        bmg.createTrivialCube(ModBlocks.MOREL_MUSHROOM_CAP.get());
        bmg.createTrivialCube(ModBlocks.JACK_O_LANTERN_MUSHROOM_CAP.get());
        bmg.createTrivialCube(ModBlocks.NECROSHROOM_CAP.get());

        fungusBlock(ModBlocks.BLEWIT_FUNGUS.get(), bsOutput, modelOutput);
        fungusBlock(ModBlocks.MOREL_FUNGUS.get(), bsOutput, modelOutput);
        fungusBlock(ModBlocks.JACK_O_LANTERN_FUNGUS.get(), bsOutput, modelOutput);
        fungusBlock(ModBlocks.NECROSHROOM_FUNGUS.get(), bsOutput, modelOutput);


        hyphaeBlock(ModBlocks.NECROSHROOM_HYPHAE.get(), bsOutput, modelOutput);


        hyphaeBlock(ModBlocks.DEATH_VINE.get(), bsOutput, modelOutput);


        saplingBlock(ModBlocks.ROTWOOD_SAPLING.get(), bsOutput, modelOutput, itemOutput);
    }



    private void overgrownGrassBlock(Block block,
                                       Consumer<BlockModelDefinitionGenerator> bsOutput,
                                       BiConsumer<Identifier, ModelInstance> modelOutput) {
        Identifier modelId = ModelLocationUtils.getModelLocation(block);
        modelOutput.accept(modelId, () -> {
            JsonObject json = new JsonObject();
            json.addProperty("parent", "minecraft:block/block");
            JsonObject textures = new JsonObject();
            textures.addProperty("particle", "minecraft:block/grass_block_top");
            textures.addProperty("all", "minecraft:block/grass_block_top");
            json.add("textures", textures);
            com.google.gson.JsonArray elements = new com.google.gson.JsonArray();
            JsonObject element = new JsonObject();
            element.add("from", jsonArr(0, 0, 0));
            element.add("to", jsonArr(16, 16, 16));
            JsonObject faces = new JsonObject();
            for (String face : new String[]{"down", "up", "north", "south", "west", "east"}) {
                JsonObject f = new JsonObject();
                f.add("uv", jsonArr(0, 0, 16, 16));
                f.addProperty("texture", "#all");
                f.addProperty("tintindex", 0);
                faces.add(face, f);
            }
            element.add("faces", faces);
            elements.add(element);
            json.add("elements", elements);
            return json;
        });
        bsOutput.accept(MultiVariantGenerator.dispatch(block, mv(modelId)));
    }

    private static com.google.gson.JsonArray jsonArr(int... vals) {
        com.google.gson.JsonArray arr = new com.google.gson.JsonArray();
        for (int v : vals) arr.add(v);
        return arr;
    }

    private void rotwoodStairs(Block stairs, TextureMapping mapping,
                                Consumer<BlockModelDefinitionGenerator> bsOutput,
                                BiConsumer<Identifier, ModelInstance> modelOutput) {
        MultiVariant straight = mv(ModelTemplates.STAIRS_STRAIGHT.create(stairs, mapping, modelOutput));
        MultiVariant inner    = mv(ModelTemplates.STAIRS_INNER.create(stairs, mapping, modelOutput));
        MultiVariant outer    = mv(ModelTemplates.STAIRS_OUTER.create(stairs, mapping, modelOutput));
        bsOutput.accept(MultiVariantGenerator.dispatch(stairs).with(
            PropertyDispatch.initial(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.HALF, BlockStateProperties.STAIRS_SHAPE)
                .select(Direction.EAST,  Half.BOTTOM, StairsShape.STRAIGHT,    straight)
                .select(Direction.WEST,  Half.BOTTOM, StairsShape.STRAIGHT,    straight.with(Y_ROT_180).with(UV_LOCK))
                .select(Direction.SOUTH, Half.BOTTOM, StairsShape.STRAIGHT,    straight.with(Y_ROT_90).with(UV_LOCK))
                .select(Direction.NORTH, Half.BOTTOM, StairsShape.STRAIGHT,    straight.with(Y_ROT_270).with(UV_LOCK))
                .select(Direction.EAST,  Half.BOTTOM, StairsShape.OUTER_RIGHT, outer)
                .select(Direction.WEST,  Half.BOTTOM, StairsShape.OUTER_RIGHT, outer.with(Y_ROT_180).with(UV_LOCK))
                .select(Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, outer.with(Y_ROT_90).with(UV_LOCK))
                .select(Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, outer.with(Y_ROT_270).with(UV_LOCK))
                .select(Direction.EAST,  Half.BOTTOM, StairsShape.OUTER_LEFT,  outer.with(Y_ROT_270).with(UV_LOCK))
                .select(Direction.WEST,  Half.BOTTOM, StairsShape.OUTER_LEFT,  outer.with(Y_ROT_90).with(UV_LOCK))
                .select(Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_LEFT,  outer)
                .select(Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_LEFT,  outer.with(Y_ROT_180).with(UV_LOCK))
                .select(Direction.EAST,  Half.BOTTOM, StairsShape.INNER_RIGHT, inner)
                .select(Direction.WEST,  Half.BOTTOM, StairsShape.INNER_RIGHT, inner.with(Y_ROT_180).with(UV_LOCK))
                .select(Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_RIGHT, inner.with(Y_ROT_90).with(UV_LOCK))
                .select(Direction.NORTH, Half.BOTTOM, StairsShape.INNER_RIGHT, inner.with(Y_ROT_270).with(UV_LOCK))
                .select(Direction.EAST,  Half.BOTTOM, StairsShape.INNER_LEFT,  inner.with(Y_ROT_270).with(UV_LOCK))
                .select(Direction.WEST,  Half.BOTTOM, StairsShape.INNER_LEFT,  inner.with(Y_ROT_90).with(UV_LOCK))
                .select(Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_LEFT,  inner)
                .select(Direction.NORTH, Half.BOTTOM, StairsShape.INNER_LEFT,  inner.with(Y_ROT_180).with(UV_LOCK))
                .select(Direction.EAST,  Half.TOP, StairsShape.STRAIGHT,    straight.with(X_ROT_180).with(UV_LOCK))
                .select(Direction.WEST,  Half.TOP, StairsShape.STRAIGHT,    straight.with(X_ROT_180).with(Y_ROT_180).with(UV_LOCK))
                .select(Direction.SOUTH, Half.TOP, StairsShape.STRAIGHT,    straight.with(X_ROT_180).with(Y_ROT_90).with(UV_LOCK))
                .select(Direction.NORTH, Half.TOP, StairsShape.STRAIGHT,    straight.with(X_ROT_180).with(Y_ROT_270).with(UV_LOCK))
                .select(Direction.EAST,  Half.TOP, StairsShape.OUTER_RIGHT, outer.with(X_ROT_180).with(Y_ROT_90).with(UV_LOCK))
                .select(Direction.WEST,  Half.TOP, StairsShape.OUTER_RIGHT, outer.with(X_ROT_180).with(Y_ROT_270).with(UV_LOCK))
                .select(Direction.SOUTH, Half.TOP, StairsShape.OUTER_RIGHT, outer.with(X_ROT_180).with(Y_ROT_180).with(UV_LOCK))
                .select(Direction.NORTH, Half.TOP, StairsShape.OUTER_RIGHT, outer.with(X_ROT_180).with(UV_LOCK))
                .select(Direction.EAST,  Half.TOP, StairsShape.OUTER_LEFT,  outer.with(X_ROT_180).with(UV_LOCK))
                .select(Direction.WEST,  Half.TOP, StairsShape.OUTER_LEFT,  outer.with(X_ROT_180).with(Y_ROT_180).with(UV_LOCK))
                .select(Direction.SOUTH, Half.TOP, StairsShape.OUTER_LEFT,  outer.with(X_ROT_180).with(Y_ROT_90).with(UV_LOCK))
                .select(Direction.NORTH, Half.TOP, StairsShape.OUTER_LEFT,  outer.with(X_ROT_180).with(Y_ROT_270).with(UV_LOCK))
                .select(Direction.EAST,  Half.TOP, StairsShape.INNER_RIGHT, inner.with(X_ROT_180).with(Y_ROT_90).with(UV_LOCK))
                .select(Direction.WEST,  Half.TOP, StairsShape.INNER_RIGHT, inner.with(X_ROT_180).with(Y_ROT_270).with(UV_LOCK))
                .select(Direction.SOUTH, Half.TOP, StairsShape.INNER_RIGHT, inner.with(X_ROT_180).with(Y_ROT_180).with(UV_LOCK))
                .select(Direction.NORTH, Half.TOP, StairsShape.INNER_RIGHT, inner.with(X_ROT_180).with(UV_LOCK))
                .select(Direction.EAST,  Half.TOP, StairsShape.INNER_LEFT,  inner.with(X_ROT_180).with(UV_LOCK))
                .select(Direction.WEST,  Half.TOP, StairsShape.INNER_LEFT,  inner.with(X_ROT_180).with(Y_ROT_180).with(UV_LOCK))
                .select(Direction.SOUTH, Half.TOP, StairsShape.INNER_LEFT,  inner.with(X_ROT_180).with(Y_ROT_90).with(UV_LOCK))
                .select(Direction.NORTH, Half.TOP, StairsShape.INNER_LEFT,  inner.with(X_ROT_180).with(Y_ROT_270).with(UV_LOCK))
        ));
    }

    private void rotwoodSlab(Block slab, TextureMapping mapping,
                              Consumer<BlockModelDefinitionGenerator> bsOutput,
                              BiConsumer<Identifier, ModelInstance> modelOutput) {
        MultiVariant bottom = mv(ModelTemplates.SLAB_BOTTOM.create(slab, mapping, modelOutput));
        MultiVariant top    = mv(ModelTemplates.SLAB_TOP.create(slab, mapping, modelOutput));
        MultiVariant full   = mv(ModelLocationUtils.getModelLocation(ModBlocks.ROTWOOD_PLANKS.get()));
        bsOutput.accept(MultiVariantGenerator.dispatch(slab).with(
            PropertyDispatch.initial(BlockStateProperties.SLAB_TYPE)
                .select(SlabType.BOTTOM, bottom)
                .select(SlabType.TOP,    top)
                .select(SlabType.DOUBLE, full)
        ));
    }

    private void rotwoodFence(Block fence, TextureMapping mapping,
                               Consumer<BlockModelDefinitionGenerator> bsOutput,
                               BiConsumer<Identifier, ModelInstance> modelOutput,
                               ItemModelOutput itemOutput) {
        MultiVariant post = mv(ModelTemplates.FENCE_POST.create(fence, mapping, modelOutput));
        MultiVariant side = mv(ModelTemplates.FENCE_SIDE.create(fence, mapping, modelOutput));
        bsOutput.accept(MultiPartGenerator.multiPart(fence)
            .with(post)
            .with(new ConditionBuilder().term(BlockStateProperties.NORTH, true), side.with(UV_LOCK))
            .with(new ConditionBuilder().term(BlockStateProperties.EAST,  true), side.with(Y_ROT_90).with(UV_LOCK))
            .with(new ConditionBuilder().term(BlockStateProperties.SOUTH, true), side.with(Y_ROT_180).with(UV_LOCK))
            .with(new ConditionBuilder().term(BlockStateProperties.WEST,  true), side.with(Y_ROT_270).with(UV_LOCK))
        );
        Identifier inventoryId = ModelTemplates.FENCE_INVENTORY.create(fence, mapping, modelOutput);
        itemOutput.accept(ModItems.ROTWOOD_FENCE_ITEM.get(), ItemModelUtils.plainModel(inventoryId));
    }

    private void rotwoodFenceGate(Block gate, TextureMapping mapping,
                                   Consumer<BlockModelDefinitionGenerator> bsOutput,
                                   BiConsumer<Identifier, ModelInstance> modelOutput) {
        MultiVariant open       = mv(ModelTemplates.FENCE_GATE_OPEN.create(gate, mapping, modelOutput));
        MultiVariant closed     = mv(ModelTemplates.FENCE_GATE_CLOSED.create(gate, mapping, modelOutput));
        MultiVariant openWall   = mv(ModelTemplates.FENCE_GATE_WALL_OPEN.create(gate, mapping, modelOutput));
        MultiVariant closedWall = mv(ModelTemplates.FENCE_GATE_WALL_CLOSED.create(gate, mapping, modelOutput));
        bsOutput.accept(MultiVariantGenerator.dispatch(gate)
            .with(PropertyDispatch.initial(BlockStateProperties.IN_WALL, BlockStateProperties.OPEN)
                .select(false, false, closed)
                .select(true,  false, closedWall)
                .select(false, true,  open)
                .select(true,  true,  openWall))
            .with(ROTATION_HORIZONTAL_FACING_ALT)
        );
    }

    private Identifier cutoutDoorModel(String variantSuffix, Identifier bottomTex, Identifier topTex,
                                        Block door, BiConsumer<Identifier, ModelInstance> modelOutput) {
        String blockPath = net.minecraft.core.registries.BuiltInRegistries.BLOCK.getKey(door).getPath();
        Identifier id = Identifier.fromNamespaceAndPath(Mycoscape.MODID, "block/" + blockPath + "_" + variantSuffix);
        modelOutput.accept(id, () -> {
            JsonObject json = new JsonObject();
            json.addProperty("render_type", "minecraft:cutout");
            json.addProperty("parent", "minecraft:block/door_" + variantSuffix);
            JsonObject textures = new JsonObject();
            textures.addProperty("bottom", bottomTex.toString());
            textures.addProperty("top", topTex.toString());
            json.add("textures", textures);
            return json;
        });
        return id;
    }

    private void rotwoodDoor(Block door,
                              Consumer<BlockModelDefinitionGenerator> bsOutput,
                              BiConsumer<Identifier, ModelInstance> modelOutput) {
        Identifier bottomTex = TextureMapping.getBlockTexture(door, "_bottom");
        Identifier topTex    = TextureMapping.getBlockTexture(door, "_top");
        MultiVariant bl  = mv(cutoutDoorModel("bottom_left",       bottomTex, topTex, door, modelOutput));
        MultiVariant blo = mv(cutoutDoorModel("bottom_left_open",  bottomTex, topTex, door, modelOutput));
        MultiVariant br  = mv(cutoutDoorModel("bottom_right",      bottomTex, topTex, door, modelOutput));
        MultiVariant bro = mv(cutoutDoorModel("bottom_right_open", bottomTex, topTex, door, modelOutput));
        MultiVariant tl  = mv(cutoutDoorModel("top_left",          bottomTex, topTex, door, modelOutput));
        MultiVariant tlo = mv(cutoutDoorModel("top_left_open",     bottomTex, topTex, door, modelOutput));
        MultiVariant tr  = mv(cutoutDoorModel("top_right",         bottomTex, topTex, door, modelOutput));
        MultiVariant tro = mv(cutoutDoorModel("top_right_open",    bottomTex, topTex, door, modelOutput));
        bsOutput.accept(MultiVariantGenerator.dispatch(door).with(
            PropertyDispatch.initial(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.DOUBLE_BLOCK_HALF,
                                     BlockStateProperties.DOOR_HINGE, BlockStateProperties.OPEN)
                .select(Direction.EAST,  DoubleBlockHalf.LOWER, DoorHingeSide.LEFT,  false, bl)
                .select(Direction.SOUTH, DoubleBlockHalf.LOWER, DoorHingeSide.LEFT,  false, bl.with(Y_ROT_90))
                .select(Direction.WEST,  DoubleBlockHalf.LOWER, DoorHingeSide.LEFT,  false, bl.with(Y_ROT_180))
                .select(Direction.NORTH, DoubleBlockHalf.LOWER, DoorHingeSide.LEFT,  false, bl.with(Y_ROT_270))
                .select(Direction.EAST,  DoubleBlockHalf.LOWER, DoorHingeSide.RIGHT, false, br)
                .select(Direction.SOUTH, DoubleBlockHalf.LOWER, DoorHingeSide.RIGHT, false, br.with(Y_ROT_90))
                .select(Direction.WEST,  DoubleBlockHalf.LOWER, DoorHingeSide.RIGHT, false, br.with(Y_ROT_180))
                .select(Direction.NORTH, DoubleBlockHalf.LOWER, DoorHingeSide.RIGHT, false, br.with(Y_ROT_270))
                .select(Direction.EAST,  DoubleBlockHalf.LOWER, DoorHingeSide.LEFT,  true,  blo.with(Y_ROT_90))
                .select(Direction.SOUTH, DoubleBlockHalf.LOWER, DoorHingeSide.LEFT,  true,  blo.with(Y_ROT_180))
                .select(Direction.WEST,  DoubleBlockHalf.LOWER, DoorHingeSide.LEFT,  true,  blo.with(Y_ROT_270))
                .select(Direction.NORTH, DoubleBlockHalf.LOWER, DoorHingeSide.LEFT,  true,  blo)
                .select(Direction.EAST,  DoubleBlockHalf.LOWER, DoorHingeSide.RIGHT, true,  bro.with(Y_ROT_270))
                .select(Direction.SOUTH, DoubleBlockHalf.LOWER, DoorHingeSide.RIGHT, true,  bro)
                .select(Direction.WEST,  DoubleBlockHalf.LOWER, DoorHingeSide.RIGHT, true,  bro.with(Y_ROT_90))
                .select(Direction.NORTH, DoubleBlockHalf.LOWER, DoorHingeSide.RIGHT, true,  bro.with(Y_ROT_180))
                .select(Direction.EAST,  DoubleBlockHalf.UPPER, DoorHingeSide.LEFT,  false, tl)
                .select(Direction.SOUTH, DoubleBlockHalf.UPPER, DoorHingeSide.LEFT,  false, tl.with(Y_ROT_90))
                .select(Direction.WEST,  DoubleBlockHalf.UPPER, DoorHingeSide.LEFT,  false, tl.with(Y_ROT_180))
                .select(Direction.NORTH, DoubleBlockHalf.UPPER, DoorHingeSide.LEFT,  false, tl.with(Y_ROT_270))
                .select(Direction.EAST,  DoubleBlockHalf.UPPER, DoorHingeSide.RIGHT, false, tr)
                .select(Direction.SOUTH, DoubleBlockHalf.UPPER, DoorHingeSide.RIGHT, false, tr.with(Y_ROT_90))
                .select(Direction.WEST,  DoubleBlockHalf.UPPER, DoorHingeSide.RIGHT, false, tr.with(Y_ROT_180))
                .select(Direction.NORTH, DoubleBlockHalf.UPPER, DoorHingeSide.RIGHT, false, tr.with(Y_ROT_270))
                .select(Direction.EAST,  DoubleBlockHalf.UPPER, DoorHingeSide.LEFT,  true,  tlo.with(Y_ROT_90))
                .select(Direction.SOUTH, DoubleBlockHalf.UPPER, DoorHingeSide.LEFT,  true,  tlo.with(Y_ROT_180))
                .select(Direction.WEST,  DoubleBlockHalf.UPPER, DoorHingeSide.LEFT,  true,  tlo.with(Y_ROT_270))
                .select(Direction.NORTH, DoubleBlockHalf.UPPER, DoorHingeSide.LEFT,  true,  tlo)
                .select(Direction.EAST,  DoubleBlockHalf.UPPER, DoorHingeSide.RIGHT, true,  tro.with(Y_ROT_270))
                .select(Direction.SOUTH, DoubleBlockHalf.UPPER, DoorHingeSide.RIGHT, true,  tro)
                .select(Direction.WEST,  DoubleBlockHalf.UPPER, DoorHingeSide.RIGHT, true,  tro.with(Y_ROT_90))
                .select(Direction.NORTH, DoubleBlockHalf.UPPER, DoorHingeSide.RIGHT, true,  tro.with(Y_ROT_180))
        ));
    }

    private Identifier cutoutTrapdoorModel(String variantSuffix, Identifier tex,
                                            Block trapdoor, BiConsumer<Identifier, ModelInstance> modelOutput) {
        String blockPath = net.minecraft.core.registries.BuiltInRegistries.BLOCK.getKey(trapdoor).getPath();
        Identifier id = Identifier.fromNamespaceAndPath(Mycoscape.MODID, "block/" + blockPath + "_" + variantSuffix);
        modelOutput.accept(id, () -> {
            JsonObject json = new JsonObject();
            json.addProperty("render_type", "minecraft:cutout");
            json.addProperty("parent", "minecraft:block/template_orientable_trapdoor_" + variantSuffix);
            JsonObject textures = new JsonObject();
            textures.addProperty("texture", tex.toString());
            json.add("textures", textures);
            return json;
        });
        return id;
    }

    private void rotwoodTrapdoor(Block trapdoor,
                                  Consumer<BlockModelDefinitionGenerator> bsOutput,
                                  BiConsumer<Identifier, ModelInstance> modelOutput,
                                  ItemModelOutput itemOutput) {
        Identifier tex      = TextureMapping.getBlockTexture(trapdoor);
        MultiVariant top    = mv(cutoutTrapdoorModel("top",    tex, trapdoor, modelOutput));
        Identifier bottomId =    cutoutTrapdoorModel("bottom", tex, trapdoor, modelOutput);
        MultiVariant bottom = mv(bottomId);
        MultiVariant open   = mv(cutoutTrapdoorModel("open",   tex, trapdoor, modelOutput));
        bsOutput.accept(MultiVariantGenerator.dispatch(trapdoor).with(
            PropertyDispatch.initial(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.HALF, BlockStateProperties.OPEN)
                .select(Direction.NORTH, Half.BOTTOM, false, bottom)
                .select(Direction.SOUTH, Half.BOTTOM, false, bottom.with(Y_ROT_180))
                .select(Direction.EAST,  Half.BOTTOM, false, bottom.with(Y_ROT_90))
                .select(Direction.WEST,  Half.BOTTOM, false, bottom.with(Y_ROT_270))
                .select(Direction.NORTH, Half.TOP,    false, top)
                .select(Direction.SOUTH, Half.TOP,    false, top.with(Y_ROT_180))
                .select(Direction.EAST,  Half.TOP,    false, top.with(Y_ROT_90))
                .select(Direction.WEST,  Half.TOP,    false, top.with(Y_ROT_270))
                .select(Direction.NORTH, Half.BOTTOM, true,  open)
                .select(Direction.SOUTH, Half.BOTTOM, true,  open.with(Y_ROT_180))
                .select(Direction.EAST,  Half.BOTTOM, true,  open.with(Y_ROT_90))
                .select(Direction.WEST,  Half.BOTTOM, true,  open.with(Y_ROT_270))
                .select(Direction.NORTH, Half.TOP,    true,  open.with(X_ROT_180).with(Y_ROT_180))
                .select(Direction.SOUTH, Half.TOP,    true,  open.with(X_ROT_180))
                .select(Direction.EAST,  Half.TOP,    true,  open.with(X_ROT_180).with(Y_ROT_270))
                .select(Direction.WEST,  Half.TOP,    true,  open.with(X_ROT_180).with(Y_ROT_90))
        ));
        itemOutput.accept(ModItems.ROTWOOD_TRAPDOOR_ITEM.get(), ItemModelUtils.plainModel(bottomId));
    }

    private void rotwoodButton(Block button, TextureMapping mapping,
                                Consumer<BlockModelDefinitionGenerator> bsOutput,
                                BiConsumer<Identifier, ModelInstance> modelOutput,
                                ItemModelOutput itemOutput) {
        MultiVariant normal  = mv(ModelTemplates.BUTTON.create(button, mapping, modelOutput));
        MultiVariant pressed = mv(ModelTemplates.BUTTON_PRESSED.create(button, mapping, modelOutput));
        bsOutput.accept(MultiVariantGenerator.dispatch(button)
            .with(PropertyDispatch.initial(BlockStateProperties.POWERED)
                .select(false, normal)
                .select(true,  pressed))
            .with(PropertyDispatch.modify(BlockStateProperties.ATTACH_FACE, BlockStateProperties.HORIZONTAL_FACING)
                .select(AttachFace.FLOOR,   Direction.EAST,  Y_ROT_90)
                .select(AttachFace.FLOOR,   Direction.WEST,  Y_ROT_270)
                .select(AttachFace.FLOOR,   Direction.SOUTH, Y_ROT_180)
                .select(AttachFace.FLOOR,   Direction.NORTH, NOP)
                .select(AttachFace.WALL,    Direction.EAST,  Y_ROT_90.then(X_ROT_90).then(UV_LOCK))
                .select(AttachFace.WALL,    Direction.WEST,  Y_ROT_270.then(X_ROT_90).then(UV_LOCK))
                .select(AttachFace.WALL,    Direction.SOUTH, Y_ROT_180.then(X_ROT_90).then(UV_LOCK))
                .select(AttachFace.WALL,    Direction.NORTH, X_ROT_90.then(UV_LOCK))
                .select(AttachFace.CEILING, Direction.EAST,  Y_ROT_270.then(X_ROT_180))
                .select(AttachFace.CEILING, Direction.WEST,  Y_ROT_90.then(X_ROT_180))
                .select(AttachFace.CEILING, Direction.SOUTH, X_ROT_180)
                .select(AttachFace.CEILING, Direction.NORTH, Y_ROT_180.then(X_ROT_180)))
        );
        Identifier inventoryId = ModelTemplates.BUTTON_INVENTORY.create(button, mapping, modelOutput);
        itemOutput.accept(ModItems.ROTWOOD_BUTTON_ITEM.get(), ItemModelUtils.plainModel(inventoryId));
    }

    private void rotwoodPressurePlate(Block plate, TextureMapping mapping,
                                       Consumer<BlockModelDefinitionGenerator> bsOutput,
                                       BiConsumer<Identifier, ModelInstance> modelOutput) {
        MultiVariant up   = mv(ModelTemplates.PRESSURE_PLATE_UP.create(plate, mapping, modelOutput));
        MultiVariant down = mv(ModelTemplates.PRESSURE_PLATE_DOWN.create(plate, mapping, modelOutput));
        bsOutput.accept(MultiVariantGenerator.dispatch(plate).with(
            PropertyDispatch.initial(BlockStateProperties.POWERED)
                .select(true,  down)
                .select(false, up)
        ));
    }



    private void leavesBlock(Block block,
                              Consumer<BlockModelDefinitionGenerator> bsOutput,
                              BiConsumer<Identifier, ModelInstance> modelOutput,
                              ItemModelOutput itemOutput) {
        Identifier modelId = ModelLocationUtils.getModelLocation(block);
        modelOutput.accept(modelId, () -> {
            JsonObject json = new JsonObject();
            json.addProperty("parent", "minecraft:block/leaves");
            JsonObject textures = new JsonObject();
            textures.addProperty("all", TextureMapping.getBlockTexture(block).toString());
            json.add("textures", textures);
            return json;
        });
        bsOutput.accept(MultiVariantGenerator.dispatch(block, mv(modelId)));
        blockItem(ModItems.ROTWOOD_LEAVES_ITEM.get(), block, itemOutput);
    }

    private void fungusBlock(Block block,
                              Consumer<BlockModelDefinitionGenerator> bsOutput,
                              BiConsumer<Identifier, ModelInstance> modelOutput) {
        String name = net.minecraft.core.registries.BuiltInRegistries.BLOCK.getKey(block).getPath();
        Identifier stage0 = Identifier.fromNamespaceAndPath(Mycoscape.MODID, "block/" + name + "_stage0");
        Identifier stage1 = Identifier.fromNamespaceAndPath(Mycoscape.MODID, "block/" + name + "_stage1");
        cutoutCrossModel(stage0, TextureMapping.getBlockTexture(block, "_stage0"), modelOutput);
        cutoutCrossModel(stage1, TextureMapping.getBlockTexture(block, "_stage1"), modelOutput);

        bsOutput.accept(
            MultiVariantGenerator.dispatch(block)
                .with(PropertyDispatch.initial(AbstractFungusBlock.AGE)
                    .select(0, mv(stage0))
                    .select(1, mv(stage1)))
        );
    }

    private void hyphaeBlock(Block block,
                              Consumer<BlockModelDefinitionGenerator> bsOutput,
                              BiConsumer<Identifier, ModelInstance> modelOutput) {
        Identifier modelId = ModelLocationUtils.getModelLocation(block);
        modelOutput.accept(modelId, () -> {
            JsonObject json = new JsonObject();
            json.addProperty("parent", "minecraft:block/cross");
            json.addProperty("render_type", "minecraft:cutout");
            json.addProperty("gui_light", "front");
            JsonObject textures = new JsonObject();
            textures.addProperty("cross", TextureMapping.getBlockTexture(block).toString());
            json.add("textures", textures);
            return json;
        });
        bsOutput.accept(MultiVariantGenerator.dispatch(block, mv(modelId)));
    }

    private void saplingBlock(Block block,
                              Consumer<BlockModelDefinitionGenerator> bsOutput,
                              BiConsumer<Identifier, ModelInstance> modelOutput,
                              ItemModelOutput itemOutput) {
        Identifier modelId = ModelLocationUtils.getModelLocation(block);
        modelOutput.accept(modelId, () -> {
            JsonObject json = new JsonObject();
            json.addProperty("parent", "minecraft:block/cross");
            json.addProperty("render_type", "minecraft:cutout");
            JsonObject textures = new JsonObject();
            textures.addProperty("cross", TextureMapping.getBlockTexture(block).toString());
            json.add("textures", textures);
            return json;
        });
        bsOutput.accept(MultiVariantGenerator.dispatch(block, mv(modelId)));
        Identifier flatModelId = ModelTemplates.FLAT_ITEM.create(
                ModItems.ROTWOOD_SAPLING_ITEM.get(),
                TextureMapping.layer0(block),
                modelOutput);
        itemOutput.accept(ModItems.ROTWOOD_SAPLING_ITEM.get(), ItemModelUtils.plainModel(flatModelId));
    }

    private void cutoutCrossModel(Identifier modelId, Identifier crossTexture,
                                   BiConsumer<Identifier, ModelInstance> modelOutput) {
        modelOutput.accept(modelId, () -> {
            JsonObject json = new JsonObject();
            json.addProperty("parent", "minecraft:block/cross");
            json.addProperty("render_type", "minecraft:cutout");
            JsonObject textures = new JsonObject();
            textures.addProperty("cross", crossTexture.toString());
            json.add("textures", textures);
            return json;
        });
    }

    private void generateItems(ItemModelOutput itemOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {

        flatItem(ModItems.BLEWIT.get(), itemOutput, modelOutput);
        flatItem(ModItems.MOREL.get(), itemOutput, modelOutput);
        flatItem(ModItems.JACK_O_LANTERN_MUSHROOM.get(), itemOutput, modelOutput);
        flatItem(ModItems.NECROSHROOM.get(), itemOutput, modelOutput);
        flatItem(ModItems.ROASTED_BLEWIT.get(), itemOutput, modelOutput);
        flatItem(ModItems.ROASTED_MOREL.get(), itemOutput, modelOutput);
        flatItem(ModItems.ROASTED_JACK_O_LANTERN_MUSHROOM.get(), itemOutput, modelOutput);
        flatItem(ModItems.ROASTED_NECROSHROOM.get(), itemOutput, modelOutput);


        blockItem(ModItems.ROTWOOD_ITEM.get(), ModBlocks.ROTWOOD.get(), itemOutput);
        blockItem(ModItems.STRIPPED_ROTWOOD_LOG_ITEM.get(), ModBlocks.STRIPPED_ROTWOOD_LOG.get(), itemOutput);
        blockItem(ModItems.ROTWOOD_WOOD_ITEM.get(), ModBlocks.ROTWOOD_WOOD.get(), itemOutput);
        blockItem(ModItems.STRIPPED_ROTWOOD_WOOD_ITEM.get(), ModBlocks.STRIPPED_ROTWOOD_WOOD.get(), itemOutput);
        blockItem(ModItems.ROTWOOD_PLANKS_ITEM.get(), ModBlocks.ROTWOOD_PLANKS.get(), itemOutput);
        blockItem(ModItems.ROTWOOD_STAIRS_ITEM.get(), ModBlocks.ROTWOOD_STAIRS.get(), itemOutput);
        blockItem(ModItems.ROTWOOD_SLAB_ITEM.get(), ModBlocks.ROTWOOD_SLAB.get(), itemOutput);

        blockItem(ModItems.ROTWOOD_FENCE_GATE_ITEM.get(), ModBlocks.ROTWOOD_FENCE_GATE.get(), itemOutput);

        blockItem(ModItems.ROTWOOD_PRESSURE_PLATE_ITEM.get(), ModBlocks.ROTWOOD_PRESSURE_PLATE.get(), itemOutput);

        blockItem(ModItems.BLEWIT_MUSHROOM_CAP_ITEM.get(), ModBlocks.BLEWIT_MUSHROOM_CAP.get(), itemOutput);
        blockItem(ModItems.MOREL_MUSHROOM_CAP_ITEM.get(), ModBlocks.MOREL_MUSHROOM_CAP.get(), itemOutput);
        blockItem(ModItems.JACK_O_LANTERN_MUSHROOM_CAP_ITEM.get(), ModBlocks.JACK_O_LANTERN_MUSHROOM_CAP.get(), itemOutput);
        blockItem(ModItems.NECROSHROOM_CAP_ITEM.get(), ModBlocks.NECROSHROOM_CAP.get(), itemOutput);

        itemOutput.accept(ModItems.OVERGROWN_GRASS_ITEM.get(),
            ItemModelUtils.tintedModel(
                ModelLocationUtils.getModelLocation(ModBlocks.OVERGROWN_GRASS.get()),
                new net.minecraft.client.color.item.GrassColorSource(0.6f, 0.8f)
            ));
        blockItem(ModItems.MYCOSLATE_ITEM.get(), ModBlocks.MYCOSLATE.get(), itemOutput);
        blockItem(ModItems.NECROSHROOM_HYPHAE_ITEM.get(), ModBlocks.NECROSHROOM_HYPHAE.get(), itemOutput);
        blockItem(ModItems.JACK_O_LANTERN_VEIN_ITEM.get(), ModBlocks.JACK_O_LANTERN_VEIN.get(), itemOutput);
        blockItem(ModItems.DEATH_VINE_ITEM.get(), ModBlocks.DEATH_VINE.get(), itemOutput);


        flatItem(ModItems.MYCELIUM_FABRIC.get(), itemOutput, modelOutput);


        flatItem(ModItems.ROTWOOD_DOOR_ITEM.get(), itemOutput, modelOutput);
    }



    private void flatItem(Item item, ItemModelOutput itemOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
        Identifier modelId = ModelTemplates.FLAT_ITEM.create(item, TextureMapping.layer0(item), modelOutput);
        itemOutput.accept(item, ItemModelUtils.plainModel(modelId));
    }

    private void blockItem(Item item, Block block, ItemModelOutput itemOutput) {
        itemOutput.accept(item, ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(block)));
    }

    private static MultiVariant mv(Identifier modelId) {
        return new MultiVariant(WeightedList.of(new Variant(modelId)));
    }

    @Override
    public String getName() {
        return "Mycoscape Model Definitions";
    }

    interface ItemModelOutput extends net.minecraft.client.data.models.ItemModelOutput {

    }
}
