package com.kosakorner.kosakore.bukkit.adapter;

import com.kosakorner.kosakore.api.adapter.IWorldGuardAdapter;
import com.kosakorner.kosakore.api.world.IWorld;
import com.kosakorner.kosakore.api.world.Location;
import com.kosakorner.kosakore.bukkit.util.ConversionUtils;
import com.kosakorner.kosakore.bukkit.world.BukkitWorld;
import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.RegionGroup;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.storage.StorageException;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BukkitWorldGuardAdapter implements IWorldGuardAdapter {

    private static WorldGuardPlugin worldGuard;

    public BukkitWorldGuardAdapter() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldGuard");
        if (plugin instanceof WorldGuardPlugin) {
            worldGuard = (WorldGuardPlugin) plugin;
        }
    }

    public void createRegion(String regionID, UUID owner, Location vectorTop, Location vectorBottom, Collection members) {
        RegionManager rgManager = worldGuard.getRegionManager(((BukkitWorld) vectorTop.getWorld()).getBackingWorld());
        ProtectedRegion rg = new ProtectedCuboidRegion(regionID, toVector(vectorTop), toVector(vectorBottom));

        DefaultDomain ownerDomain = new DefaultDomain();
        ownerDomain.addPlayer(owner);
        rg.setOwners(ownerDomain);

        DefaultDomain memberDomain = new DefaultDomain();
        for (Object object : members) {
            memberDomain.addPlayer(UUID.fromString(object.toString()));
        }
        rg.setMembers(memberDomain);

        ApplicableRegionSet set = rgManager.getApplicableRegions(rg);
        for (ProtectedRegion conflictingRegion : set) {
            if (!conflictingRegion.getId().equalsIgnoreCase("__global__")) {
                rgManager.removeRegion(conflictingRegion.getId());
            }
        }

        rg.setFlag(DefaultFlag.CHEST_ACCESS.getRegionGroupFlag(), RegionGroup.OWNERS);
        rg.setFlag(DefaultFlag.CHEST_ACCESS.getRegionGroupFlag(), RegionGroup.MEMBERS);

        rgManager.addRegion(rg);
        try {
            rgManager.save();
        } catch (StorageException e) {
            e.printStackTrace();
        }
    }

    public void updateRegion(String regionID, UUID owner, Location vectorTop, Location vectorBottom, Collection members) {
        RegionManager rgManager = worldGuard.getRegionManager(((BukkitWorld) vectorTop.getWorld()).getBackingWorld());
        ProtectedRegion rg = rgManager.getRegion(regionID);

        if (rg != null) {
            DefaultDomain ownerDomain = new DefaultDomain();
            ownerDomain.addPlayer(owner);
            rg.setOwners(ownerDomain);

            DefaultDomain memberDomain = new DefaultDomain();
            for (Object object : members) {
                memberDomain.addPlayer(UUID.fromString(object.toString()));
            }
            rg.setMembers(memberDomain);

            rg.setDirty(true);
        }

        try {
            rgManager.save();
        } catch (StorageException e) {
            e.printStackTrace();
        }
    }

    public void removeRegion(String regionID, IWorld world) {
        RegionManager rgManager = worldGuard.getRegionManager(((BukkitWorld) world).getBackingWorld());
        ProtectedRegion rg = rgManager.getRegion(regionID);

        if (rg != null) {
            rgManager.removeRegion(rg.getId());
        }

        try {
            rgManager.save();
        } catch (StorageException e) {
            e.printStackTrace();
        }
    }

    public void setGreetMessage(String regionID, IWorld world, String message) {
        RegionManager rgManager = worldGuard.getRegionManager(((BukkitWorld) world).getBackingWorld());
        ProtectedRegion rg = rgManager.getRegion(regionID);
        if (rg != null) {
            rg.setFlag(DefaultFlag.GREET_MESSAGE, parseFlag(message));
            rg.setDirty(true);
        }
        try {
            rgManager.save();
        } catch (StorageException e) {
            e.printStackTrace();
        }
    }

    public void setFarewellMessage(String regionID, IWorld world, String message) {
        RegionManager rgManager = worldGuard.getRegionManager(((BukkitWorld) world).getBackingWorld());
        ProtectedRegion rg = rgManager.getRegion(regionID);
        if (rg != null) {
            rg.setFlag(DefaultFlag.FAREWELL_MESSAGE, parseFlag(message));
            rg.setDirty(true);
        }
        try {
            rgManager.save();
        } catch (StorageException e) {
            e.printStackTrace();
        }
    }

    public void setPVP(String regionID, IWorld world, boolean state) {
        RegionManager rgManager = worldGuard.getRegionManager(((BukkitWorld) world).getBackingWorld());
        ProtectedRegion rg = rgManager.getRegion(regionID);
        if (rg != null) {
            rg.setFlag(DefaultFlag.PVP, state ? StateFlag.State.ALLOW : StateFlag.State.DENY);
            rg.setDirty(true);
        }
        try {
            rgManager.save();
        } catch (StorageException e) {
            e.printStackTrace();
        }
    }

    public Set<String> getApplicableRegions(Location location) {
        Set<ProtectedRegion> regions = worldGuard.getRegionManager(Bukkit.getWorld(location.getWorld().getName())).getApplicableRegions(ConversionUtils.fromKoreLocation(location)).getRegions();
        Set<String> matches = new HashSet<String>();
        for (ProtectedRegion region : regions) {
            matches.add(region.getId());
        }
        return matches;
    }

    private static String parseFlag(String string) {
        return string.replaceAll("(?!\\\\)\\\\n", "\n").replaceAll("\\\\\\\\n", "\\n");
    }

    private static BlockVector toVector(Location location) {
        return new BlockVector(location.getX(), location.getY(), location.getZ());
    }

}
