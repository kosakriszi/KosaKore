package com.kosakore.canary.util;

import com.kosakore.canary.item.CanaryItemStack;
import com.kosakore.canary.world.CanaryWorld;
import com.kosakorner.kosakore.api.item.IItemStack;
import com.kosakorner.kosakore.api.world.EnvironmentType;
import net.canarymod.Canary;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.DimensionType;
import net.canarymod.api.world.position.Location;

public class ConversionUtils {

    public static IItemStack toKoreItemStack(Item stack) {
        return new CanaryItemStack(stack);
    }

    public static Item fromKoreItemStack(IItemStack stack) {
        return ((CanaryItemStack) stack).getBackingItem();
    }

    public static com.kosakorner.kosakore.api.world.Location toKoreLocation(Location location) {
        if (location != null) {
            return new com.kosakorner.kosakore.api.world.Location(new CanaryWorld(location.getWorld()), location.getX(), location.getY(), location.getZ(), location.getRotation(), location.getPitch());
        }
        else {
            return null;
        }
    }

    public static Location fromKoreLocation(com.kosakorner.kosakore.api.world.Location location) {
        if (location != null) {
            return new Location(Canary.getServer().getWorld(location.getWorld().getName()), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        }
        else {
            return null;
        }
    }

    public static EnvironmentType toKoreEnvironment(DimensionType environment) {
        if (environment.equals(DimensionType.NORMAL)) {
            return EnvironmentType.NORMAL;
        }
        else if (environment.equals(DimensionType.NETHER)) {
            return EnvironmentType.NETHER;
        }
        else if (environment.equals(DimensionType.END)) {
            return EnvironmentType.THE_END;
        }
        else {
            return EnvironmentType.NORMAL;
        }
    }

    public static DimensionType fromKoreEnvironment(EnvironmentType environment) {
        switch (environment) {
            case NORMAL:
                return DimensionType.NORMAL;
            case NETHER:
                return DimensionType.NETHER;
            case THE_END:
                return DimensionType.END;
            default:
                return DimensionType.NORMAL;
        }
    }

}
