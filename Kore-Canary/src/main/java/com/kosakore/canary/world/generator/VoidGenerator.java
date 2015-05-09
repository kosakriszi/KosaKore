package com.kosakore.canary.world.generator;

import net.canarymod.Canary;
import net.canarymod.api.world.Chunk;
import net.canarymod.api.world.ChunkProviderCustom;

public class VoidGenerator extends ChunkProviderCustom {

    public Chunk provideChunk(int x, int z) {
        Chunk chunk = Canary.factory().getObjectFactory().newChunk(world, x, z);
        return chunk;
    }

    public void populate(int x, int z) {

    }

    public void createStructures(int x, int z) {

    }

}
