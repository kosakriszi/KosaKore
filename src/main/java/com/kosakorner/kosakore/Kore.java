package com.kosakorner.kosakore;

import com.kosakorner.kosakore.compat.VaultInterface;
import com.kosakorner.kosakore.util.LocationUtils;
import com.kosakorner.kosakore.util.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Kore extends JavaPlugin {

    private PlayerUtils   playerUtils;
    private LocationUtils locationUtils;

    public static Kore instance() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("SkyBlock");
        if (plugin instanceof Kore) {
            return (Kore) plugin;
        }
        return null;
    }

    public void onEnable() {
        VaultInterface.setupEconomy();
        VaultInterface.setupPermissions();

        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        playerUtils = new PlayerUtils();
        locationUtils = new LocationUtils();

        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(playerUtils, this);
    }

    public void onDisable() {
        playerUtils.writeUUIDMap();
    }

    public static PlayerUtils playerUtils() {
        return instance().playerUtils;
    }

    public static LocationUtils locationUtils() {
        return instance().locationUtils;
    }

}
