package com.dpesic.mycoscape.datagen;

import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider implements DataProvider {
    public ModItemTagProvider(PackOutput output) {}

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        return CompletableFuture.allOf();
    }

    @Override
    public String getName() {
        return "Mycoscape Item Tags";
    }
}
