package com.dpesic.mycoscape.datagen;

import com.dpesic.mycoscape.core.ModBlocks;
import com.dpesic.mycoscape.core.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(PackOutput output, String modId, String locale) {
        super(output, modId, locale);
    }

    @Override
    protected void addTranslations() {
        // ---- Mushroom food items ---------------------------------------------
        addItem(ModItems.BLEWIT,                    "Blewit");
        addItem(ModItems.MOREL,                     "Morel");
        addItem(ModItems.JACK_O_LANTERN_MUSHROOM,   "Jack o'Lantern Mushroom");
        addItem(ModItems.NECROSHROOM,               "Necroshroom");

        // ---- Crafting materials ---------------------------------------------
        addItem(ModItems.MYCELIUM_FABRIC,           "Mycelium Fabric");

        // ---- Big mushroom caps -----------------------------------------------
        addBlock(ModBlocks.BLEWIT_MUSHROOM_CAP,            "Blewit Mushroom Cap");
        addBlock(ModBlocks.MOREL_MUSHROOM_CAP,             "Morel Mushroom Cap");
        addBlock(ModBlocks.JACK_O_LANTERN_MUSHROOM_CAP,    "Jack o'Lantern Mushroom Cap");
        addBlock(ModBlocks.NECROSHROOM_CAP,                "Necroshroom Cap");
        addBlock(ModBlocks.MUSHROOM_STEM,                  "Mushroom Stem");

        // ---- Rotwood wood set -----------------------------------------------
        addBlock(ModBlocks.ROTWOOD_LEAVES,          "Rotwood Leaves");
        addBlock(ModBlocks.ROTWOOD,                 "Rotwood Log");
        addBlock(ModBlocks.STRIPPED_ROTWOOD_LOG,    "Stripped Rotwood Log");
        addBlock(ModBlocks.ROTWOOD_WOOD,            "Rotwood Wood");
        addBlock(ModBlocks.STRIPPED_ROTWOOD_WOOD,   "Stripped Rotwood Wood");
        addBlock(ModBlocks.ROTWOOD_PLANKS,          "Rotwood Planks");
        addBlock(ModBlocks.ROTWOOD_STAIRS,          "Rotwood Stairs");
        addBlock(ModBlocks.ROTWOOD_SLAB,            "Rotwood Slab");
        addBlock(ModBlocks.ROTWOOD_FENCE,           "Rotwood Fence");
        addBlock(ModBlocks.ROTWOOD_FENCE_GATE,      "Rotwood Fence Gate");
        addBlock(ModBlocks.ROTWOOD_DOOR,            "Rotwood Door");
        addBlock(ModBlocks.ROTWOOD_TRAPDOOR,        "Rotwood Trapdoor");
        addBlock(ModBlocks.ROTWOOD_BUTTON,          "Rotwood Button");
        addBlock(ModBlocks.ROTWOOD_PRESSURE_PLATE,  "Rotwood Pressure Plate");

        // ---- Terrain & decoration -------------------------------------------
        addBlock(ModBlocks.FUNGAL_SUBSTRATE,        "Fungal Substrate");
        addBlock(ModBlocks.MYCOSLATE,               "Mycoslate");
        addBlock(ModBlocks.NECROSHROOM_HYPHAE,      "Necroshroom Hyphae");
        addBlock(ModBlocks.JACK_O_LANTERN_VEIN,     "Jack o'Lantern Vein");
        addBlock(ModBlocks.FUNGAL_CONDUIT,          "Fungal Conduit");
        addBlock(ModBlocks.DEATH_VINE,              "Death Vine");

        // ---- Biomes ---------------------------------------------------------
        add("biome.mycoscape.mycoscape_surface", "Mycoscape");
        add("biome.mycoscape.mycoscape_caves",   "Mycoscape Caves");
    }
}
