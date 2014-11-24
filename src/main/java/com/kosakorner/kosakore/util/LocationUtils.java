package com.kosakorner.kosakore.util;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;

import java.util.regex.Pattern;

public class LocationUtils {

    public String toString(Location location) {
        return location.getWorld().getName() + "," + location.getX() + "," + location.getY() + "," + location.getZ() + "," + location.getYaw();
    }

    public Location fromString(String string) {
        String[] parts = string.split(Pattern.quote(","));
        Location parsed = new Location(Bukkit.getWorld(parts[0]), Double.valueOf(parts[1]), Double.valueOf(parts[2]), Double.valueOf(parts[3]));
        parsed.setYaw(Float.valueOf(parts[4]));
        return parsed;
    }

    public void removeMobs(Location location, int radius) {
        if (location == null) {
            return;
        }

        int px = location.getBlockX();
        int py = location.getBlockY();
        int pz = location.getBlockZ();
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                Chunk c = location.getWorld().getChunkAt(new Location(location.getWorld(), px + x * 16, py, pz + z * 16));
                for (Entity e : c.getEntities()) {
                    switch (e.getType()) {
                        case SPIDER:
                        case CREEPER:
                        case ENDERMAN:
                        case SKELETON:
                        case ZOMBIE:
                        case WITCH:
                            e.remove();
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    public boolean isLocationSafe(Location location) {
        if (location == null) {
            return false;
        }

        Block above = location.getBlock().getRelative(BlockFace.UP);
        Block block = location.getBlock();
        Block below = location.getBlock().getRelative(BlockFace.DOWN);

        if (below.getType().equals(Material.AIR)) {
            return false;
        }
        if (below.getType().equals(Material.LAVA)) {
            return false;
        }
        if (below.getType().equals(Material.STATIONARY_LAVA)) {
            return false;
        }
        if (below.getType().equals(Material.CACTUS)) {
            return false;
        }
        if ((
                    block.getType().equals(Material.AIR) ||
                    block.getType().equals(Material.TORCH) ||
                    block.getType().equals(Material.REDSTONE_TORCH_OFF) ||
                    block.getType().equals(Material.REDSTONE_TORCH_ON) ||
                    block.getType().equals(Material.REDSTONE_WIRE) ||
                    block.getType().equals(Material.CROPS) ||
                    block.getType().equals(Material.CARROT) ||
                    block.getType().equals(Material.POTATO) ||
                    block.getType().equals(Material.LONG_GRASS) ||
                    block.getType().equals(Material.RED_ROSE) ||
                    block.getType().equals(Material.YELLOW_FLOWER) ||
                    block.getType().equals(Material.MELON_STEM) ||
                    block.getType().equals(Material.PUMPKIN_STEM) ||
                    block.getType().equals(Material.DEAD_BUSH) ||
                    block.getType().equals(Material.SIGN_POST) ||
                    block.getType().equals(Material.SIGN) ||
                    block.getType().equals(Material.DOUBLE_PLANT)
            ) && (above.getType().equals(Material.AIR))) {
            return true;
        }
        return false;
    }

}
