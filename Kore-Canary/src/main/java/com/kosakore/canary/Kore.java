package com.kosakore.canary;

import com.kosakore.canary.command.CanaryDispatcherFactory;
import com.kosakore.canary.entity.CanaryPlayer;
import com.kosakore.canary.item.CanaryItemFactory;
import com.kosakore.canary.world.CanaryWorldFactory;
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
import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.logger.Logman;
import net.canarymod.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Kore extends Plugin implements IKore {

    private static IKore instance;

    private static Logman log = Logman.getLogman("KosaKore");

    private ItemFactory        itemFactory;
    private IWorldFactory      worldFactory;
    private IDispatcherFactory dispatcherFactory;
    private EventBus           eventBus;
    private HandlerRegistry    handlerRegistry;
    private ModuleLoader       moduleLoader;

    public static IKore instance() {
        return instance;
    }

    public boolean enable() {
        instance = this;

        File moduleDir = new File(Canary.getWorkingDirectory(), "modules");

        itemFactory = new CanaryItemFactory();
        worldFactory = new CanaryWorldFactory();
        dispatcherFactory = new CanaryDispatcherFactory();
        eventBus = new EventBus(this);
        handlerRegistry = new HandlerRegistry();

        moduleLoader = new ModuleLoader(this, moduleDir);

        log("Modules");
        for (String name : moduleLoader.listModules()) {
            log("Loaded: " + name);
        }

        return true;
    }

    public static void log(String message) {
        log.info(message);
    }

    public void disable() {

    }

    public IPlayer getPlayer(UUID uuid) {
        return null;
    }

    public UUID getUUIDFromName(String name) {
        return null;
    }

    public String getNameFromUUID(UUID uuid) {
        return Canary.getServer().getPlayerFromUUID(uuid).getName();
    }

    public List<IPlayer> getPlayers() {
        List<IPlayer> toReturn = new ArrayList<IPlayer>();
        for (Player player : Canary.getServer().getPlayerList()) {
            toReturn.add(new CanaryPlayer(player));
        }
        return toReturn;
    }

    public List<IPlayer> matchPlayer(String partialName) {
        List<IPlayer> matchedPlayers = new ArrayList<IPlayer>();

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
        return null;
    }

    public File getWorkingDir() {
        return new File(getConfig().getFilePath()).getParentFile();
    }

}
