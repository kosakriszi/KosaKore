package com.kosakorner.kosakore.bukkit.world;

import com.kosakorner.kosakore.api.item.IItemStack;
import com.kosakorner.kosakore.api.world.IBlock;
import com.kosakorner.kosakore.api.world.IChunk;
import com.kosakorner.kosakore.api.world.IWorld;
import com.kosakorner.kosakore.api.world.Location;
import com.kosakorner.kosakore.bukkit.util.ConversionUtils;
import org.bukkit.World;

public class BukkitWorld implements IWorld {

    private World backingWorld;

    public BukkitWorld(World world) {
        backingWorld = world;
    }

    public World getBackingWorld() {
        return backingWorld;
    }

    public String getName() {
        return backingWorld.getName();
    }

    public IBlock getBlockAt(Location location) {
        return new BukkitBlock(backingWorld.getBlockAt(ConversionUtils.fromKoreLocation(location)));
    }

    public IBlock getBlockAt(int x, int y, int z) {
        return new BukkitBlock(backingWorld.getBlockAt(x, y, z));
    }

    public IChunk getChunkAt(Location location) {
        return new BukkitChunk(backingWorld.getChunkAt(ConversionUtils.fromKoreLocation(location)));
    }

    public void dropItem(Location location, IItemStack stack) {
        backingWorld.dropItem(ConversionUtils.fromKoreLocation(location), ConversionUtils.fromKoreItemStack(stack));
    }


}
