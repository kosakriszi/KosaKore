package com.kosakore.canary.world;

import com.kosakore.canary.block.CanaryBlock;
import com.kosakorner.kosakore.api.block.IBlock;
import com.kosakorner.kosakore.api.world.IChunk;
import com.kosakorner.kosakore.api.world.IWorld;
import com.kosakorner.kosakore.api.world.Location;
import net.canarymod.api.world.World;

public class CanaryWorld implements IWorld {

    private World backingWorld;

    public CanaryWorld(World backingWorld) {
        this.backingWorld = backingWorld;
    }

    public String getName() {
        return null;
    }

    public IBlock getBlockAt(Location location) {
        return null;
    }

    public IBlock getBlockAt(int x, int y, int z) {
        return new CanaryBlock(backingWorld.getBlockAt(x, y, z));
    }

    public IChunk getChunkAt(Location location) {
        return new CanaryChunk(backingWorld.getChunk(location.getBlockX(), location.getBlockZ()));
    }

}
