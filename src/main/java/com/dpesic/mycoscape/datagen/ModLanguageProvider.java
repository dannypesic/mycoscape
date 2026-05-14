package com.dpesic.mycoscape.datagen;

import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

import java.util.concurrent.CompletableFuture;

public class ModLanguageProvider implements DataProvider {
    public ModLanguageProvider(PackOutput output, String modId, String locale) {}

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        return CompletableFuture.allOf();
    }

    @Override
    public String getName() {
        return "Mycoscape Language";
    }
}
