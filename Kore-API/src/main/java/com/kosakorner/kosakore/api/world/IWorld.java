package com.kosakorner.kosakore.api.world;

public interface IWorld {

    String getName();

    IBlock getBlockAt(Location location);

    IBlock getBlockAt(int x, int y, int z);

    IChunk getChunkAt(Location location);

}
