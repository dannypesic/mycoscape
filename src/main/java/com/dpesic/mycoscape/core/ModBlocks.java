package com.dpesic.mycoscape.core;

import com.dpesic.mycoscape.block.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import java.util.Optional;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.level.block.SoundType;
import static com.dpesic.mycoscape.core.BlockProps.ToolTier.*;


public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Registries.BLOCK, Mycoscape.MODID);



    public static final DeferredHolder<Block, BlewitFungusBlock> BLEWIT_FUNGUS = BLOCKS.register(
            "blewit_fungus",
            () -> new BlewitFungusBlock(
                    BlockBehaviour.Properties.of()
                            .noCollission()
                            .randomTicks()
                            .instabreak()
                            .sound(SoundType.ROOTS)
            )
    );

    public static final DeferredHolder<Block, MorelFungusBlock> MOREL_FUNGUS = BLOCKS.register(
            "morel_fungus",
            () -> new MorelFungusBlock(
                    BlockBehaviour.Properties.of()
                            .noCollission()
                            .randomTicks()
                            .instabreak()
                            .sound(SoundType.ROOTS)
            )
    );

    public static final DeferredHolder<Block, JackOLanternFungusBlock> JACK_O_LANTERN_FUNGUS = BLOCKS.register(
            "jack_o_lantern_fungus",
            () -> new JackOLanternFungusBlock(
                    BlockBehaviour.Properties.of()
                            .noCollission()
                            .randomTicks()
                            .instabreak()
                            .sound(SoundType.ROOTS)
            )
    );

    public static final DeferredHolder<Block, NecroshroomFungusBlock> NECROSHROOM_FUNGUS = BLOCKS.register(
            "necroshroom_fungus",
            () -> new NecroshroomFungusBlock(
                    BlockBehaviour.Properties.of()
                            .noCollission()
                            .randomTicks()
                            .instabreak()
                            .sound(SoundType.ROOTS)
            )
    );



    public static final DeferredHolder<Block, HugeMushroomBlock> BLEWIT_MUSHROOM_CAP = BLOCKS.register(
            "blewit_mushroom_cap",
            () -> new HugeMushroomBlock(
                    BlockBehaviour.Properties.of()
                            .strength(0.2f)
                            .sound(SoundType.WOOD)
            )
    );

    public static final DeferredHolder<Block, HugeMushroomBlock> MOREL_MUSHROOM_CAP = BLOCKS.register(
            "morel_mushroom_cap",
            () -> new HugeMushroomBlock(
                    BlockBehaviour.Properties.of()
                            .strength(0.2f)
                            .sound(SoundType.WOOD)
            )
    );

    public static final DeferredHolder<Block, HugeMushroomBlock> JACK_O_LANTERN_MUSHROOM_CAP = BLOCKS.register(
            "jack_o_lantern_mushroom_cap",
            () -> new HugeMushroomBlock(
                    BlockBehaviour.Properties.of()
                            .strength(0.2f)
                            .sound(SoundType.WOOD)
                            .lightLevel(state -> 10)
            )
    );

    public static final DeferredHolder<Block, HugeMushroomBlock> NECROSHROOM_CAP = BLOCKS.register(
            "necroshroom_cap",
            () -> new HugeMushroomBlock(
                    BlockBehaviour.Properties.of()
                            .strength(0.2f)
                            .sound(SoundType.WOOD)
            )
    );


    public static final DeferredHolder<Block, RotatedPillarBlock> ROTWOOD = BLOCKS.register(
            "rotwood",
            () -> new RotatedPillarBlock(
                    BlockBehaviour.Properties.of()
                            .sound(SoundType.MANGROVE_ROOTS)
                            .destroyTime(0.5f)
                            .explosionResistance(0.5f)
            )
    );

    public static final DeferredHolder<Block, RotatedPillarBlock> STRIPPED_ROTWOOD_LOG = BLOCKS.register(
            "stripped_rotwood_log",
            () -> new RotatedPillarBlock(
                    BlockBehaviour.Properties.of()
                            .sound(SoundType.WOOD)
                            .destroyTime(0.5f)
                            .explosionResistance(0.5f)
            )
    );

    public static final DeferredHolder<Block, RotatedPillarBlock> ROTWOOD_WOOD = BLOCKS.register(
            "rotwood_wood",
            () -> new RotatedPillarBlock(
                    BlockBehaviour.Properties.of()
                            .sound(SoundType.WOOD)
                            .destroyTime(0.5f)
                            .explosionResistance(0.5f)
            )
    );

    public static final DeferredHolder<Block, RotatedPillarBlock> STRIPPED_ROTWOOD_WOOD = BLOCKS.register(
            "stripped_rotwood_wood",
            () -> new RotatedPillarBlock(
                    BlockBehaviour.Properties.of()
                            .sound(SoundType.WOOD)
                            .destroyTime(0.5f)
                            .explosionResistance(0.5f)
            )
    );

    public static final DeferredHolder<Block, Block> ROTWOOD_PLANKS = BLOCKS.register(
            "rotwood_planks",
            () -> new Block(
                    BlockBehaviour.Properties.of()
                            .sound(SoundType.WOOD)
                            .destroyTime(2.0f)
                            .explosionResistance(3.0f)
            )
    );

    public static final DeferredHolder<Block, StairBlock> ROTWOOD_STAIRS = BLOCKS.register(
            "rotwood_stairs",
            () -> new StairBlock(
                    ROTWOOD_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.of()
                            .sound(SoundType.WOOD)
                            .destroyTime(2.0f)
                            .explosionResistance(3.0f)
            )
    );

    public static final DeferredHolder<Block, SlabBlock> ROTWOOD_SLAB = BLOCKS.register(
            "rotwood_slab",
            () -> new SlabBlock(
                    BlockBehaviour.Properties.of()
                            .sound(SoundType.WOOD)
                            .destroyTime(2.0f)
                            .explosionResistance(3.0f)
            )
    );

    public static final DeferredHolder<Block, FenceBlock> ROTWOOD_FENCE = BLOCKS.register(
            "rotwood_fence",
            () -> new FenceBlock(
                    BlockBehaviour.Properties.of()
                            .sound(SoundType.WOOD)
                            .destroyTime(2.0f)
                            .explosionResistance(3.0f)
            )
    );

    public static final DeferredHolder<Block, FenceGateBlock> ROTWOOD_FENCE_GATE = BLOCKS.register(
            "rotwood_fence_gate",
            () -> new FenceGateBlock(
                    WoodType.OAK,
                    BlockBehaviour.Properties.of()
                            .sound(SoundType.WOOD)
                            .destroyTime(2.0f)
                            .explosionResistance(3.0f)
            )
    );

    public static final DeferredHolder<Block, DoorBlock> ROTWOOD_DOOR = BLOCKS.register(
            "rotwood_door",
            () -> new DoorBlock(
                    BlockSetType.OAK,
                    BlockBehaviour.Properties.of()
                            .sound(SoundType.WOOD)
                            .destroyTime(3.0f)
                            .explosionResistance(3.0f)
                            .noOcclusion()
            )
    );

    public static final DeferredHolder<Block, TrapDoorBlock> ROTWOOD_TRAPDOOR = BLOCKS.register(
            "rotwood_trapdoor",
            () -> new TrapDoorBlock(
                    BlockSetType.OAK,
                    BlockBehaviour.Properties.of()
                            .sound(SoundType.WOOD)
                            .destroyTime(3.0f)
                            .explosionResistance(3.0f)
                            .noOcclusion()
            )
    );

    public static final DeferredHolder<Block, ButtonBlock> ROTWOOD_BUTTON = BLOCKS.register(
            "rotwood_button",
            () -> new ButtonBlock(
                    BlockSetType.OAK,
                    30,
                    BlockBehaviour.Properties.of()
                            .sound(SoundType.WOOD)
                            .noCollission()
                            .destroyTime(0.5f)
            )
    );

    public static final DeferredHolder<Block, PressurePlateBlock> ROTWOOD_PRESSURE_PLATE = BLOCKS.register(
            "rotwood_pressure_plate",
            () -> new PressurePlateBlock(
                    BlockSetType.OAK,
                    BlockBehaviour.Properties.of()
                            .sound(SoundType.WOOD)
                            .noCollission()
                            .destroyTime(0.5f)
            )
    );

    private static final ResourceKey<ConfiguredFeature<?, ?>> ROTWOOD_TREE_CF =
            ResourceKey.create(Registries.CONFIGURED_FEATURE,
                    ResourceLocation.fromNamespaceAndPath(Mycoscape.MODID, "rotwood_tree"));

    public static final TreeGrower ROTWOOD_TREE_GROWER = new TreeGrower(
            "rotwood", Optional.empty(), Optional.of(ROTWOOD_TREE_CF), Optional.empty());

    public static final DeferredHolder<Block, SaplingBlock> ROTWOOD_SAPLING = BLOCKS.register(
            "rotwood_sapling",
            () -> new SaplingBlock(
                    ROTWOOD_TREE_GROWER,
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.PLANT)
                            .noCollission()
                            .randomTicks()
                            .instabreak()
                            .sound(SoundType.GRASS)
                            .pushReaction(PushReaction.DESTROY)
            )
    );

    public static final DeferredHolder<Block, RotwoodLeavesBlock> ROTWOOD_LEAVES = BLOCKS.register(
            "rotwood_leaves",
            () -> new RotwoodLeavesBlock(
                    0.02f,
                    BlockBehaviour.Properties.of()
                            .strength(0.2f)
                            .sound(SoundType.GRASS)
                            .noOcclusion()
                            .randomTicks()
                            .isValidSpawn((state, level, pos, type) -> false)
                            .isSuffocating((state, level, pos) -> false)
                            .isViewBlocking((state, level, pos) -> false)
            )
    );



    public static final DeferredHolder<Block, Block> OVERGROWN_GRASS = BLOCKS.register(
            "overgrown_grass",
            () -> new Block(
                    BlockBehaviour.Properties.of()
                            .sound(SoundType.MUDDY_MANGROVE_ROOTS)
                            .destroyTime(0.5f)
                            .explosionResistance(0.5f)
            )
    );

    public static final DeferredHolder<Block, Block> MYCOSLATE = BLOCKS.register(
            "mycoslate",
            () -> new Block(
                    BlockBehaviour.Properties.of()
                            .sound(SoundType.DEEPSLATE)
                            .destroyTime(BlockProps.ticks(15, STONE))
                            .requiresCorrectToolForDrops()
            )
    );

    public static final DeferredHolder<Block, HyphaeBlock> NECROSHROOM_HYPHAE = BLOCKS.register(
            "necroshroom_hyphae",
            () -> new HyphaeBlock(
                    BlockBehaviour.Properties.of()
                            .destroyTime(0.05f)
                            .noCollission()
                            .sound(SoundType.ROOTS)
            )
    );

    public static final DeferredHolder<Block, Block> JACK_O_LANTERN_VEIN = BLOCKS.register(
            "jack_o_lantern_vein",
            () -> new Block(
                    BlockBehaviour.Properties.of()
                            .destroyTime(0.5f)
                            .sound(SoundType.ROOTS)
                            .lightLevel((state) -> 8)
            )
    );



    public static final DeferredHolder<Block, DeathVineBlock> DEATH_VINE = BLOCKS.register(
            "death_vine",
            () -> new DeathVineBlock(
                    BlockBehaviour.Properties.of()
                            .noCollission()
                            .destroyTime(2.0f)
                            .sound(SoundType.ROOTS)
            )
    );

    public static final DeferredHolder<Block, RotwoodLeafLitterBlock> ROTWOOD_LEAF_LITTER = BLOCKS.register(
            "rotwood_leaf_litter",
            () -> new RotwoodLeafLitterBlock(
                    BlockBehaviour.Properties.of()
                            .noCollission()
                            .instabreak()
                            .sound(SoundType.GRASS)
            )
    );

}
