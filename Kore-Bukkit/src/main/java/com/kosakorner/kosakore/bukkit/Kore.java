package com.kosakorner.kosakore.bukkit;

import com.kosakorner.kosakore.api.IKore;
import com.kosakorner.kosakore.api.adapter.Adapters;
import com.kosakorner.kosakore.api.adapter.IAdapter;
import com.kosakorner.kosakore.api.command.IDispatcherFactory;
import com.kosakorner.kosakore.api.entity.IPlayer;
import com.kosakorner.kosakore.api.event.EventBus;
import com.kosakorner.kosakore.api.event.HandlerRegistry;
import com.kosakorner.kosakore.api.item.ItemFactory;
import com.kosakorner.kosakore.api.module.ModuleLoader;
import com.kosakorner.kosakore.api.world.IWorldFactory;
import com.kosakorner.kosakore.bukkit.adapter.BukkitEconomyAdapter;
import com.kosakorner.kosakore.bukkit.adapter.BukkitPermissionAdapter;
import com.kosakorner.kosakore.bukkit.adapter.BukkitWorldEditAdapter;
import com.kosakorner.kosakore.bukkit.adapter.BukkitWorldGuardAdapter;
import com.kosakorner.kosakore.bukkit.command.BukkitDispatcherFactory;
import com.kosakorner.kosakore.bukkit.entity.BukkitPlayer;
import com.kosakorner.kosakore.bukkit.event.BukkitEventHandler;
import com.kosakorner.kosakore.bukkit.item.BukkitItemFactory;
import com.kosakorner.kosakore.bukkit.util.PlayerUtils;
import com.kosakorner.kosakore.bukkit.world.BukkitWorldFactory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.*;
import java.util.logging.Logger;

public class Kore extends JavaPlugin implements IKore {

    private static IKore instance;

    private static Logger log = Logger.getLogger("Minecraft");

    private PlayerUtils playerUtils;

    private ItemFactory        itemFactory;
    private IWorldFactory      worldFactory;
    private IDispatcherFactory dispatcherFactory;
    private EventBus           eventBus;
    private HandlerRegistry    handlerRegistry;
    private ModuleLoader       moduleLoader;

    private Map<Adapters, IAdapter> adapters;

    public static IKore instance() {
        return instance;
    }

    public void onEnable() {
        instance = this;

        File moduleDir = new File(Bukkit.getWorldContainer(), "modules");

        itemFactory = new BukkitItemFactory();
        worldFactory = new BukkitWorldFactory();
        dispatcherFactory = new BukkitDispatcherFactory();
        eventBus = new EventBus(this);
        handlerRegistry = new HandlerRegistry();

        if (getDataFolder().mkdir()) {
            log("Creating directories.");
        }

        playerUtils = new PlayerUtils();

        adapters = new HashMap<>();
        adapters.put(Adapters.WORLDEDIT, new BukkitWorldEditAdapter());
        adapters.put(Adapters.WORLDGUARD, new BukkitWorldGuardAdapter());
        adapters.put(Adapters.PERMISSION, new BukkitPermissionAdapter());
        adapters.put(Adapters.ECONOMY, new BukkitEconomyAdapter());

        moduleLoader = new ModuleLoader(this, moduleDir);

        log("Modules");
        for (String name : moduleLoader.listModules()) {
            log("Loaded: " + name);
        }

        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(playerUtils, this);
        manager.registerEvents(new BukkitEventHandler(this), this);
    }

    public void onDisable() {
        playerUtils.writeUUIDMap();
    }

    public static void log(String message) {
        log.info("[KosaKore] " + message);
    }

    public IPlayer getPlayer(UUID uuid) {
        return new BukkitPlayer(Bukkit.getPlayer(uuid));
    }

    public UUID getUUIDFromName(String name) {
        return playerUtils.getUUIDFromName(name);
    }

    public String getNameFromUUID(UUID uuid) {
        return playerUtils.getNameFromUUID(uuid);
    }

    public List<IPlayer> getPlayers() {
        List<IPlayer> toReturn = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            toReturn.add(new BukkitPlayer(player));
        }
        return toReturn;
    }

    public List<IPlayer> matchPlayer(String partialName) {
        List<IPlayer> matchedPlayers = new ArrayList<>();

        for (IPlayer player : getPlayers()) {
            String name = player.getName();
            if (partialName.equalsIgnoreCase(name)) {
                matchedPlayers.clear();
                matchedPlayers.add(player);
                break;
            }

            if (name.toLowerCase().contains(partialName.toLowerCase())) {
                matchedPlayers.add(player);
            }
        }
        return matchedPlayers;
    }

    public ItemFactory itemFactory() {
        return itemFactory;
    }

    public IWorldFactory worldFactory() {
        return worldFactory;
    }

    public IDispatcherFactory dispatcherFactory() {
        return dispatcherFactory;
    }

    public EventBus eventBus() {
        return eventBus;
    }

    public HandlerRegistry handlerRegistry() {
        return handlerRegistry;
    }

    public ModuleLoader moduleLoader() {
        return moduleLoader;
    }

    public IAdapter getAdapter(Adapters type) {
        return adapters.get(type);
    }

    public File getWorkingDir() {
        return getDataFolder();
    }

}
