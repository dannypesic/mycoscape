package com.dpesic.mycoscape.datagen;

import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider implements DataProvider {
    public ModBlockTagProvider(PackOutput output) {}

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        return CompletableFuture.allOf();
    }

    @Override
    public String getName() {
        return "Mycoscape Block Tags";
    }
}
