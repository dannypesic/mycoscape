package com.dpesic.mycoscape.datagen;

import com.dpesic.mycoscape.core.ModBlocks;
import com.dpesic.mycoscape.core.ModItems;
import com.dpesic.mycoscape.tags.MycoscapeItemTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        HolderGetter<Item> items = this.registries.lookupOrThrow(Registries.ITEM);

        // ---- Rotwood wood set (planks from log) ------------------------------
        ShapelessRecipeBuilder.shapeless(items, RecipeCategory.BUILDING_BLOCKS, ModItems.ROTWOOD_PLANKS_ITEM.get(), 4)
                .requires(ModItems.ROTWOOD_ITEM.get())
                .unlockedBy("has_rotwood", has(ModBlocks.ROTWOOD.get()))
                .save(output, "rotwood_planks_from_log");

        ShapelessRecipeBuilder.shapeless(items, RecipeCategory.BUILDING_BLOCKS, ModItems.ROTWOOD_PLANKS_ITEM.get(), 4)
                .requires(ModItems.STRIPPED_ROTWOOD_LOG_ITEM.get())
                .unlockedBy("has_rotwood", has(ModBlocks.ROTWOOD.get()))
                .save(output, "rotwood_planks_from_stripped_log");

        ShapelessRecipeBuilder.shapeless(items, RecipeCategory.BUILDING_BLOCKS, ModItems.ROTWOOD_PLANKS_ITEM.get(), 4)
                .requires(ModItems.ROTWOOD_WOOD_ITEM.get())
                .unlockedBy("has_rotwood", has(ModBlocks.ROTWOOD.get()))
                .save(output, "rotwood_planks_from_wood");

        ShapelessRecipeBuilder.shapeless(items, RecipeCategory.BUILDING_BLOCKS, ModItems.ROTWOOD_PLANKS_ITEM.get(), 4)
                .requires(ModItems.STRIPPED_ROTWOOD_WOOD_ITEM.get())
                .unlockedBy("has_rotwood", has(ModBlocks.ROTWOOD.get()))
                .save(output, "rotwood_planks_from_stripped_wood");

        // rotwood_wood: 2x rotwood_log in a 2x2 → 3x rotwood_wood
        ShapedRecipeBuilder.shaped(items, RecipeCategory.BUILDING_BLOCKS, ModItems.ROTWOOD_WOOD_ITEM.get(), 3)
                .define('L', ModItems.ROTWOOD_ITEM.get())
                .pattern("LL")
                .pattern("LL")
                .unlockedBy("has_rotwood", has(ModBlocks.ROTWOOD.get()))
                .save(output);

        // stairs: 6x planks in stair shape → 4x stairs
        ShapedRecipeBuilder.shaped(items, RecipeCategory.BUILDING_BLOCKS, ModItems.ROTWOOD_STAIRS_ITEM.get(), 4)
                .define('P', ModItems.ROTWOOD_PLANKS_ITEM.get())
                .pattern("P  ")
                .pattern("PP ")
                .pattern("PPP")
                .unlockedBy("has_rotwood_planks", has(ModBlocks.ROTWOOD_PLANKS.get()))
                .save(output);

        // slab: 3x planks in a row → 6x slabs
        ShapedRecipeBuilder.shaped(items, RecipeCategory.BUILDING_BLOCKS, ModItems.ROTWOOD_SLAB_ITEM.get(), 6)
                .define('P', ModItems.ROTWOOD_PLANKS_ITEM.get())
                .pattern("PPP")
                .unlockedBy("has_rotwood_planks", has(ModBlocks.ROTWOOD_PLANKS.get()))
                .save(output);

        // fence: PSPS / PSPS → 3x fence
        ShapedRecipeBuilder.shaped(items, RecipeCategory.DECORATIONS, ModItems.ROTWOOD_FENCE_ITEM.get(), 3)
                .define('P', ModItems.ROTWOOD_PLANKS_ITEM.get())
                .define('S', Items.STICK)
                .pattern("PSP")
                .pattern("PSP")
                .unlockedBy("has_rotwood_planks", has(ModBlocks.ROTWOOD_PLANKS.get()))
                .save(output);

        // fence gate: SPS / SPS → 1x fence gate
        ShapedRecipeBuilder.shaped(items, RecipeCategory.DECORATIONS, ModItems.ROTWOOD_FENCE_GATE_ITEM.get())
                .define('P', ModItems.ROTWOOD_PLANKS_ITEM.get())
                .define('S', Items.STICK)
                .pattern("SPS")
                .pattern("SPS")
                .unlockedBy("has_rotwood_planks", has(ModBlocks.ROTWOOD_PLANKS.get()))
                .save(output);

        // door: PP / PP / PP → 3x doors
        ShapedRecipeBuilder.shaped(items, RecipeCategory.REDSTONE, ModItems.ROTWOOD_DOOR_ITEM.get(), 3)
                .define('P', ModItems.ROTWOOD_PLANKS_ITEM.get())
                .pattern("PP")
                .pattern("PP")
                .pattern("PP")
                .unlockedBy("has_rotwood_planks", has(ModBlocks.ROTWOOD_PLANKS.get()))
                .save(output);

        // trapdoor: PPP / PPP → 2x trapdoors
        ShapedRecipeBuilder.shaped(items, RecipeCategory.REDSTONE, ModItems.ROTWOOD_TRAPDOOR_ITEM.get(), 2)
                .define('P', ModItems.ROTWOOD_PLANKS_ITEM.get())
                .pattern("PPP")
                .pattern("PPP")
                .unlockedBy("has_rotwood_planks", has(ModBlocks.ROTWOOD_PLANKS.get()))
                .save(output);

        // button: 1x plank → 1x button
        ShapelessRecipeBuilder.shapeless(items, RecipeCategory.REDSTONE, ModItems.ROTWOOD_BUTTON_ITEM.get())
                .requires(ModItems.ROTWOOD_PLANKS_ITEM.get())
                .unlockedBy("has_rotwood_planks", has(ModBlocks.ROTWOOD_PLANKS.get()))
                .save(output);

        // pressure plate: PP → 1x pressure plate
        ShapedRecipeBuilder.shaped(items, RecipeCategory.REDSTONE, ModItems.ROTWOOD_PRESSURE_PLATE_ITEM.get())
                .define('P', ModItems.ROTWOOD_PLANKS_ITEM.get())
                .pattern("PP")
                .unlockedBy("has_rotwood_planks", has(ModBlocks.ROTWOOD_PLANKS.get()))
                .save(output);

        // ---- Mycelium Fabric ------------------------------------------------
        // Any 2 custom mushrooms → 1 mycelium_fabric
        ShapelessRecipeBuilder.shapeless(items, RecipeCategory.MISC, ModItems.MYCELIUM_FABRIC.get())
                .requires(MycoscapeItemTags.CUSTOM_MUSHROOMS)
                .requires(MycoscapeItemTags.CUSTOM_MUSHROOMS)
                .unlockedBy("has_custom_mushroom", has(MycoscapeItemTags.CUSTOM_MUSHROOMS))
                .save(output, "mycelium_fabric_from_mushrooms");

        // 2 mycelium_fabric → 1 white wool
        ShapelessRecipeBuilder.shapeless(items, RecipeCategory.BUILDING_BLOCKS, Items.WHITE_WOOL)
                .requires(ModItems.MYCELIUM_FABRIC.get())
                .requires(ModItems.MYCELIUM_FABRIC.get())
                .unlockedBy("has_mycelium_fabric", has(ModItems.MYCELIUM_FABRIC.get()))
                .save(output, "white_wool_from_mycelium_fabric");

        // ---- Death Vine -----------------------------------------------------
        ShapelessRecipeBuilder.shapeless(items, RecipeCategory.DECORATIONS, ModItems.DEATH_VINE_ITEM.get())
                .requires(ModItems.NECROSHROOM.get())
                .requires(ModItems.MYCELIUM_FABRIC.get())
                .unlockedBy("has_necroshroom", has(ModItems.NECROSHROOM.get()))
                .save(output, "death_vine");

        // ---- Fungal substrate -----------------------------------------------
        ShapedRecipeBuilder.shaped(items, RecipeCategory.MISC, ModItems.FUNGAL_SUBSTRATE_ITEM.get())
                .define('A', Items.BROWN_MUSHROOM)
                .define('B', ModItems.MOREL.get())
                .define('C', Items.BONE_MEAL)
                .define('D', ModItems.BLEWIT.get())
                .define('E', Items.ROOTED_DIRT)
                .define('F', ModItems.JACK_O_LANTERN_MUSHROOM.get())
                .define('G', Items.RED_MUSHROOM)
                .pattern("BDF")
                .pattern("AEG")
                .pattern("CCC")
                .unlockedBy("has_blewit", has(ModItems.BLEWIT.get()))
                .save(output);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new ModRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "Mycoscape Recipes";
        }
    }
}
