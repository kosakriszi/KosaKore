package com.kosakorner.kosakore.bukkit;

import com.kosakorner.kosakore.api.IKore;
import com.kosakorner.kosakore.api.entity.IPlayer;
import com.kosakorner.kosakore.api.item.IItemFactory;
import com.kosakorner.kosakore.api.module.ModuleLoader;
import com.kosakorner.kosakore.api.world.IWorldFactory;
import com.kosakorner.kosakore.bukkit.entity.BukkitPlayer;
import com.kosakorner.kosakore.bukkit.item.BukkitItemFactory;
import com.kosakorner.kosakore.bukkit.util.PlayerUtils;
import com.kosakorner.kosakore.bukkit.world.BukkitWorldFactory;
import com.kosakorner.kosakore.net.Downloader;
import com.kosakorner.kosakore.net.IDownloadListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class Kore extends JavaPlugin implements IKore {

    private static IKore instance;

    private static Logger log = Logger.getLogger("Minecraft");

    private PlayerUtils playerUtils;

    private ModuleLoader moduleLoader;

    private IItemFactory  itemFactory;
    private IWorldFactory worldFactory;

    public static IKore instance() {
        return instance;
    }

    public void onEnable() {
        instance = this;

        File moduleDir = new File(getDataFolder().getParentFile().getParentFile(), "modules");
        moduleDir.mkdir();
        moduleLoader = new ModuleLoader(moduleDir);

        log("Modules");
        for (String name : moduleLoader.listModules()) {
            log("Loaded: " + name);
        }

        itemFactory = new BukkitItemFactory();
        worldFactory = new BukkitWorldFactory();

        if (getDataFolder().mkdir()) {
            log("Creating directories.");
        }

        playerUtils = new PlayerUtils();

        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(playerUtils, this);
    }

    public void onDisable() {
        playerUtils.writeUUIDMap();
    }

    public static void log(String message) {
        log.info("[KosaKore]: " + message);
    }

    public PlayerUtils playerUtils() {
        return playerUtils;
    }

    public ModuleLoader getModuleLoader() {
        return moduleLoader;
    }

    public File getWorkingDir() {
        return getDataFolder();
    }

    public IPlayer getPlayer(UUID uuid) {
        return new BukkitPlayer(Bukkit.getPlayer(uuid));
    }

    public List<IPlayer> getPlayers() {
        List<IPlayer> toReturn = new ArrayList<IPlayer>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            toReturn.add(new BukkitPlayer(player));
        }
        return toReturn;
    }

    public IItemFactory itemFactory() {
        return itemFactory;
    }

    public IWorldFactory worldFactory() {
        return worldFactory;
    }

}
