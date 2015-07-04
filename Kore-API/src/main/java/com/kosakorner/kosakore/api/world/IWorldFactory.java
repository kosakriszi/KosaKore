package com.kosakorner.kosakore.api.world;

public interface IWorldFactory {

    IWorld getWorld(String name);

    IWorld createWorld(String name, WorldType worldType, DimensionType envType);

}
