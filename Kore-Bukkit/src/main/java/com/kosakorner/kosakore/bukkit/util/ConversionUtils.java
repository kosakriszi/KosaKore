package com.kosakorner.kosakore.bukkit.util;

import com.kosakorner.kosakore.api.item.IItemStack;
import com.kosakorner.kosakore.api.world.DimensionType;
import com.kosakorner.kosakore.bukkit.item.BukkitItemStack;
import com.kosakorner.kosakore.bukkit.world.BukkitWorld;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

public class ConversionUtils {

    public static IItemStack toKoreItemStack(ItemStack stack) {
        return new BukkitItemStack(stack);
    }

    public static ItemStack fromKoreItemStack(IItemStack stack) {
        return ((BukkitItemStack) stack).getBackingItem();
    }

    public static com.kosakorner.kosakore.api.world.Location toKoreLocation(Location location) {
        if (location != null) {
            return new com.kosakorner.kosakore.api.world.Location(new BukkitWorld(location.getWorld()), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        }
        else {
            return null;
        }
    }

    public static Location fromKoreLocation(com.kosakorner.kosakore.api.world.Location location) {
        if (location != null) {
            return new Location(Bukkit.getWorld(location.getWorld().getName()), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        }
        else {
            return null;
        }
    }

    public static DimensionType toKoreEnvironment(World.Environment environment) {
        switch (environment) {
            case NORMAL:
                return DimensionType.NORMAL;
            case NETHER:
                return DimensionType.NETHER;
            case THE_END:
                return DimensionType.THE_END;
            default:
                return DimensionType.NORMAL;
        }
    }

    public static World.Environment fromKoreEnvironment(DimensionType environment) {
        switch (environment) {
            case NORMAL:
                return World.Environment.NORMAL;
            case NETHER:
                return World.Environment.NETHER;
            case THE_END:
                return World.Environment.THE_END;
            default:
                return World.Environment.NORMAL;
        }
    }

}
