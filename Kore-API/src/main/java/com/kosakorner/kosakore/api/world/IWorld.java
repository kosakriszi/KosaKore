package com.kosakorner.kosakore.api.world;

import com.kosakorner.kosakore.api.block.IBlock;

public interface IWorld {

    public String getName();

    public IBlock getBlockAt(Location location);

    public IBlock getBlockAt(int x, int y, int z);

    public IChunk getChunkAt(Location location);

}
