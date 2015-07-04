package com.kosakore.canary.world;

import com.kosakore.canary.world.generator.VoidGenerator;
import com.kosakorner.kosakore.api.world.DimensionType;
import com.kosakorner.kosakore.api.world.IWorld;
import com.kosakorner.kosakore.api.world.IWorldFactory;
import com.kosakorner.kosakore.api.world.WorldType;
import net.canarymod.Canary;
import net.canarymod.api.world.World;

public class CanaryWorldFactory implements IWorldFactory {

    public CanaryWorldFactory() {
        net.canarymod.api.world.DimensionType.registerType("VOID", 0, VoidGenerator.class);
    }

    public IWorld getWorld(String name) {
        return new CanaryWorld(Canary.getServer().getWorld(name));
    }

    public IWorld createWorld(String name, WorldType worldType, DimensionType envType) {
        switch (worldType) {
            case NORMAL:
                break;
            case CUSTOM:
                break;
            case VOID:
                Canary.getServer().getWorldManager().createWorld(name, 0, net.canarymod.api.world.DimensionType.fromName("VOID"), net.canarymod.api.world.WorldType.SUPERFLAT);
                World world = Canary.getServer().getWorld(name);
                return new CanaryWorld(world);
        }
        return null;
    }

}
