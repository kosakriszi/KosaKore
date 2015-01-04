package com.kosakorner.kosakore.bukkit;

import com.kosakorner.kosakore.api.IKore;
import com.kosakorner.kosakore.api.item.IItemFactory;
import com.kosakorner.kosakore.api.world.IWorld;
import com.kosakorner.kosakore.api.world.IWorldFactory;
import com.kosakorner.kosakore.bukkit.compat.Vault;
import com.kosakorner.kosakore.bukkit.item.BukkitItemFactory;
import com.kosakorner.kosakore.bukkit.util.LocationUtils;
import com.kosakorner.kosakore.bukkit.util.PlayerUtils;
import com.kosakorner.kosakore.bukkit.world.BukkitWorldFactory;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Kore extends JavaPlugin implements IKore {

    private static Logger log = Logger.getLogger("Minecraft");

    private PlayerUtils   playerUtils;
    private LocationUtils locationUtils;

    private IItemFactory itemFactory;
    private IWorldFactory worldFactory;

    public static Kore instance() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("KosaKore");
        if (plugin instanceof Kore) {
            return (Kore) plugin;
        }
        return null;
    }

    public void onEnable() {
        itemFactory = new BukkitItemFactory();
        worldFactory = new BukkitWorldFactory();

        if (getDataFolder().mkdir()) {
            log("Creating directories.");
        }

        playerUtils = new PlayerUtils();
        locationUtils = new LocationUtils();

        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(playerUtils, this);
    }

    public void onDisable() {
        playerUtils.writeUUIDMap();
    }

    public static void log(String message) {
        log.info("[KosaKore]: " + message);
    }

    public static PlayerUtils playerUtils() {
        return instance().playerUtils;
    }

    public static LocationUtils locationUtils() {
        return instance().locationUtils;
    }

    public IItemFactory itemFactory() {
        return itemFactory;
    }

    public IWorldFactory worldFactory() {
        return worldFactory;
    }

    public IWorld getWorld(String name) {
        return null;
    }
}
