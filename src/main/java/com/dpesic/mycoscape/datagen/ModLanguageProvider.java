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

        addItem(ModItems.BLEWIT,                            "Blewit");
        addItem(ModItems.MOREL,                             "Morel");
        addItem(ModItems.JACK_O_LANTERN_MUSHROOM,           "Jack o'Lantern Mushroom");
        addItem(ModItems.NECROSHROOM,                       "Necroshroom");
        addItem(ModItems.ROASTED_BLEWIT,                    "Roasted Blewit");
        addItem(ModItems.ROASTED_MOREL,                     "Roasted Morel");
        addItem(ModItems.ROASTED_JACK_O_LANTERN_MUSHROOM,   "Roasted Jack o'Lantern Mushroom");
        addItem(ModItems.ROASTED_NECROSHROOM,               "Roasted Necroshroom");


        addItem(ModItems.MYCELIUM_FABRIC,           "Mycelium Fabric");


        addBlock(ModBlocks.BLEWIT_FUNGUS,                  "Blewit Fungus");
        addBlock(ModBlocks.MOREL_FUNGUS,                   "Morel Fungus");
        addBlock(ModBlocks.JACK_O_LANTERN_FUNGUS,          "Jack o'Lantern Fungus");
        addBlock(ModBlocks.NECROSHROOM_FUNGUS,             "Necroshroom Fungus");

        addBlock(ModBlocks.BLEWIT_MUSHROOM_CAP,            "Blewit Mushroom Cap");
        addBlock(ModBlocks.MOREL_MUSHROOM_CAP,             "Morel Mushroom Cap");
        addBlock(ModBlocks.JACK_O_LANTERN_MUSHROOM_CAP,    "Jack o'Lantern Mushroom Cap");
        addBlock(ModBlocks.NECROSHROOM_CAP,                "Necroshroom Cap");
        addItem(ModItems.BLEWIT_MUSHROOM_CAP_ITEM,         "Blewit Mushroom Cap");
        addItem(ModItems.MOREL_MUSHROOM_CAP_ITEM,          "Morel Mushroom Cap");
        addItem(ModItems.JACK_O_LANTERN_MUSHROOM_CAP_ITEM, "Jack o'Lantern Mushroom Cap");
        addItem(ModItems.NECROSHROOM_CAP_ITEM,             "Necroshroom Cap");

        addBlock(ModBlocks.ROTWOOD_SAPLING,         "Rotwood Sapling");
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
        addItem(ModItems.ROTWOOD_SAPLING_ITEM,          "Rotwood Sapling");
        addItem(ModItems.ROTWOOD_LEAVES_ITEM,           "Rotwood Leaves");
        addItem(ModItems.ROTWOOD_ITEM,                  "Rotwood Log");
        addItem(ModItems.STRIPPED_ROTWOOD_LOG_ITEM,     "Stripped Rotwood Log");
        addItem(ModItems.ROTWOOD_WOOD_ITEM,             "Rotwood Wood");
        addItem(ModItems.STRIPPED_ROTWOOD_WOOD_ITEM,    "Stripped Rotwood Wood");
        addItem(ModItems.ROTWOOD_PLANKS_ITEM,           "Rotwood Planks");
        addItem(ModItems.ROTWOOD_STAIRS_ITEM,           "Rotwood Stairs");
        addItem(ModItems.ROTWOOD_SLAB_ITEM,             "Rotwood Slab");
        addItem(ModItems.ROTWOOD_FENCE_ITEM,            "Rotwood Fence");
        addItem(ModItems.ROTWOOD_FENCE_GATE_ITEM,       "Rotwood Fence Gate");
        addItem(ModItems.ROTWOOD_DOOR_ITEM,             "Rotwood Door");
        addItem(ModItems.ROTWOOD_TRAPDOOR_ITEM,         "Rotwood Trapdoor");
        addItem(ModItems.ROTWOOD_BUTTON_ITEM,           "Rotwood Button");
        addItem(ModItems.ROTWOOD_PRESSURE_PLATE_ITEM,   "Rotwood Pressure Plate");

        addBlock(ModBlocks.OVERGROWN_GRASS,        "Overgrown Grass");
        addBlock(ModBlocks.MYCOSLATE,               "Mycoslate");
        addBlock(ModBlocks.NECROSHROOM_HYPHAE,      "Necroshroom Hyphae");
        addBlock(ModBlocks.JACK_O_LANTERN_VEIN,     "Jack o'Lantern Vein");
addBlock(ModBlocks.DEATH_VINE,              "Death Vine");
        addItem(ModItems.OVERGROWN_GRASS_ITEM,     "Overgrown Grass");
        addItem(ModItems.MYCOSLATE_ITEM,            "Mycoslate");
        addItem(ModItems.NECROSHROOM_HYPHAE_ITEM,   "Necroshroom Hyphae");
        addItem(ModItems.JACK_O_LANTERN_VEIN_ITEM,  "Jack o'Lantern Vein");
        addItem(ModItems.DEATH_VINE_ITEM,           "Death Vine");


        add("itemGroup.mycoscape.mycoscape", "Mycoscape");

        add("biome.mycoscape.fungal_forest",    "Fungal Forest");
        add("biome.mycoscape.necrotic_caverns", "Necrotic Caverns");
    }
}
