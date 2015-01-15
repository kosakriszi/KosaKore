package com.kosakorner.kosakore.bukkit.world;

import com.kosakorner.kosakore.api.world.EnvironmentType;
import com.kosakorner.kosakore.api.world.IWorld;
import com.kosakorner.kosakore.api.world.IWorldFactory;
import com.kosakorner.kosakore.api.world.WorldType;
import com.kosakorner.kosakore.bukkit.util.ConversionUtils;
import com.kosakorner.kosakore.bukkit.world.generator.VoidGenerator;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class BukkitWorldFactory implements IWorldFactory {

    public IWorld getWorld(String name) {
        return new BukkitWorld(Bukkit.getWorld(name));
    }

    // TODO: implement fully
    public IWorld createWorld(String name, WorldType worldType, EnvironmentType envType) {
        switch (worldType) {
            case NORMAL:
                break;
            case CUSTOM:
                break;
            case VOID:
                return new BukkitWorld(WorldCreator.name(name).type(org.bukkit.WorldType.FLAT).environment(ConversionUtils.fromKoreEnvironment(envType)).generator(new VoidGenerator()).createWorld());
        }
        return null;
    }

}
