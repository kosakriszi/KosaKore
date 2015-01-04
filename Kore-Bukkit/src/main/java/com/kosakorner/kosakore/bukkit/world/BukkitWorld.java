package com.kosakorner.kosakore.bukkit.world;

import com.kosakorner.kosakore.api.block.IBlock;
import com.kosakorner.kosakore.api.world.IWorld;
import com.kosakorner.kosakore.api.world.Location;
import com.kosakorner.kosakore.bukkit.Kore;
import com.kosakorner.kosakore.bukkit.block.BukkitBlock;
import org.bukkit.World;

public class BukkitWorld implements IWorld {

    private World backingWorld;

    public BukkitWorld(World world) {
        backingWorld = world;
    }

    public String getName() {
        return backingWorld.getName();
    }

    public IBlock getBlockAt(Location location) {
        return new BukkitBlock(backingWorld.getBlockAt(Kore.locationUtils().fromKoreLocation(location)));
    }

    public IBlock getBlockAt(int x, int y, int z) {
        return new BukkitBlock(backingWorld.getBlockAt(x, y, z));
    }

}
