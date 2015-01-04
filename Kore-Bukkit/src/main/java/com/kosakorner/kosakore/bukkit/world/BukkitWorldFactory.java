package com.kosakorner.kosakore.bukkit.world;

import com.kosakorner.kosakore.api.world.EnvironmentType;
import com.kosakorner.kosakore.api.world.IWorld;
import com.kosakorner.kosakore.api.world.IWorldFactory;
import com.kosakorner.kosakore.api.world.WorldType;
import org.bukkit.Bukkit;

public class BukkitWorldFactory implements IWorldFactory {

    public IWorld getWorld(String name) {
        return new BukkitWorld(Bukkit.getWorld(name));
    }

    public IWorld createWorld(String name, WorldType worldType, EnvironmentType envType) {
        return null;
    }
}
