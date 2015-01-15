package com.kosakorner.kosakore.bukkit.world.generator;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class VoidGenerator extends ChunkGenerator {

    public List<BlockPopulator> getDefaultPopulators(World world) {
        return Arrays.asList(new BlockPopulator[0]);
    }

    public boolean canSpawn(World world, int x, int z) {
        return true;
    }

    public byte[] generate(World world, Random rand, int chunkX, int chunkZ) {
        return new byte[32768];
    }

    public Location getFixedSpawnLocation(World world, Random random) {
        return new Location(world, 0.0D, 128.0D, 0.0D);
    }

}
