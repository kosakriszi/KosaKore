package com.kosakorner.kosakore.bukkit.adapter;

import com.kosakorner.kosakore.api.adapter.IWorldEditAdapter;
import com.kosakorner.kosakore.api.world.Location;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.schematic.SchematicFormat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class BukkitWorldEditAdapter implements IWorldEditAdapter {

    private static WorldEditPlugin worldEdit;

    public BukkitWorldEditAdapter() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldEdit");
        if (plugin instanceof WorldEditPlugin) {
            worldEdit = (WorldEditPlugin) plugin;
        }
    }

    public void pasteSchematic(Location location, File schematic) {
        try {
            Vector vector = new Vector(location.getBlockX(), location.getBlockY(), location.getBlockZ());
            SchematicFormat format = SchematicFormat.getFormat(schematic);
            if (format == null) {
                throw new RuntimeException("Whoever made your schematic was on the drugas... Fix the format!");
            }
            EditSession session = worldEdit.getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(((com.kosakorner.kosakore.bukkit.world.BukkitWorld) location.getWorld()).getBackingWorld()), 999999999);
            CuboidClipboard cc = format.load(schematic);
            cc.paste(session, vector, false);
        } catch (Exception e) {
            throw new RuntimeException("Unable to load schematic file! Oh noes!", e);
        }
    }

}
