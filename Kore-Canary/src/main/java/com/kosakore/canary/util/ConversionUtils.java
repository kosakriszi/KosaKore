package com.kosakore.canary.util;

import com.kosakore.canary.item.CanaryItemStack;
import com.kosakore.canary.world.CanaryWorld;
import com.kosakorner.kosakore.api.item.IItemStack;
import com.kosakorner.kosakore.api.world.DimensionType;
import net.canarymod.Canary;
import net.canarymod.api.inventory.Item;
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

    public static DimensionType toKoreEnvironment(net.canarymod.api.world.DimensionType environment) {
        if (environment.equals(net.canarymod.api.world.DimensionType.NORMAL)) {
            return DimensionType.NORMAL;
        }
        else if (environment.equals(net.canarymod.api.world.DimensionType.NETHER)) {
            return DimensionType.NETHER;
        }
        else if (environment.equals(net.canarymod.api.world.DimensionType.END)) {
            return DimensionType.THE_END;
        }
        else {
            return DimensionType.NORMAL;
        }
    }

    public static net.canarymod.api.world.DimensionType fromKoreEnvironment(DimensionType environment) {
        switch (environment) {
            case NORMAL:
                return net.canarymod.api.world.DimensionType.NORMAL;
            case NETHER:
                return net.canarymod.api.world.DimensionType.NETHER;
            case THE_END:
                return net.canarymod.api.world.DimensionType.END;
            default:
                return net.canarymod.api.world.DimensionType.NORMAL;
        }
    }

}
