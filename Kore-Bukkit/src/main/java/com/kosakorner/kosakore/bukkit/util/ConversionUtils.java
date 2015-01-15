package com.kosakorner.kosakore.bukkit.util;

import com.kosakorner.kosakore.api.item.IItemStack;
import com.kosakorner.kosakore.api.world.EnvironmentType;
import com.kosakorner.kosakore.bukkit.Kore;
import com.kosakorner.kosakore.bukkit.item.BukkitItemStack;
import com.kosakorner.kosakore.bukkit.world.BukkitWorld;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ConversionUtils {

    public static IItemStack toKoreItemStack(ItemStack stack) {
        return new BukkitItemStack(stack);
    }

    // TODO: add enchantment conversions
    public static ItemStack fromKoreItemStack(IItemStack stack) {
        ItemStack toReturn = new ItemStack(Material.getMaterial(stack.getType().name()), stack.getStackSize(), stack.getDurability());
        ItemMeta meta = toReturn.getItemMeta();
        meta.setDisplayName(stack.getName());
        toReturn.setItemMeta(meta);
        return toReturn;
    }

    public static com.kosakorner.kosakore.api.world.Location toKoreLocation(Location location) {
        return new com.kosakorner.kosakore.api.world.Location(new BukkitWorld(location.getWorld()), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    public static Location fromKoreLocation(com.kosakorner.kosakore.api.world.Location location) {
        return new Location(Bukkit.getWorld(location.getWorld().getName()), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    public static EnvironmentType toKoreEnvironment(World.Environment environment) {
        switch (environment) {
            case NORMAL:
                return EnvironmentType.NORMAL;
            case NETHER:
                return EnvironmentType.NETHER;
            case THE_END:
                return EnvironmentType.THE_END;
            default:
                return EnvironmentType.NORMAL;
        }
    }

    public static World.Environment fromKoreEnvironment(EnvironmentType environment) {
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
