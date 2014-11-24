package com.kosakorner.kosakore;

import com.kosakorner.kosakore.compat.VaultInterface;
import com.kosakorner.kosakore.util.LocationUtils;
import com.kosakorner.kosakore.util.PlayerUtils;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Kore extends JavaPlugin {

    private static Kore          instance;
    private        PlayerUtils   playerUtils;
    private        LocationUtils locationUtils;

    public void onEnable() {
        instance = this;

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

    public static Kore instance() {
        return instance;
    }

    public static PlayerUtils playerUtils() {
        return instance.playerUtils;
    }

    public static LocationUtils locationUtils() {
        return instance.locationUtils;
    }

}
