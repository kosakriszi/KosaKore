package com.kosakorner.kosakore.api.world;

public interface IWorldFactory {

    public IWorld getWorld(String name);

    public IWorld createWorld(String name, WorldType worldType, EnvironmentType envType);

}
