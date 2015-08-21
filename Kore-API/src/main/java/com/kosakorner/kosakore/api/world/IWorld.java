package com.kosakorner.kosakore.api.world;

import com.kosakorner.kosakore.api.item.IItemStack;

public interface IWorld {

    String getName();

    IBlock getBlockAt(Location location);

    IBlock getBlockAt(int x, int y, int z);

    IChunk getChunkAt(Location location);

    void dropItem(Location location, IItemStack stack);

}
