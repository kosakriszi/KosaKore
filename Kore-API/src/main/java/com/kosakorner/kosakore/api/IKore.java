package com.kosakorner.kosakore.api;

import com.kosakorner.kosakore.api.adapter.Adapters;
import com.kosakorner.kosakore.api.adapter.IAdapter;
import com.kosakorner.kosakore.api.command.IDispatcherFactory;
import com.kosakorner.kosakore.api.entity.IPlayer;
import com.kosakorner.kosakore.api.event.EventBus;
import com.kosakorner.kosakore.api.event.HandlerRegistry;
import com.kosakorner.kosakore.api.item.ItemFactory;
import com.kosakorner.kosakore.api.module.ModuleLoader;
import com.kosakorner.kosakore.api.world.IWorldFactory;

import java.io.File;
import java.util.List;
import java.util.UUID;

public interface IKore {

    public IPlayer getPlayer(UUID uuid);

    public IPlayer getOfflinePlayer(UUID uuid);

    public UUID getUUIDFromName(String name);

    public String getNameFromUUID(UUID uuid);

    public List<IPlayer> getPlayers();

    public List<IPlayer> matchPlayer(String partialName);

    public ItemFactory itemFactory();

    public IWorldFactory worldFactory();

    public IDispatcherFactory dispatcherFactory();

    public EventBus eventBus();

    public HandlerRegistry handlerRegistry();

    public ModuleLoader moduleLoader();

    public IAdapter getAdapter(Adapters type);

    public File getWorkingDir();

    public void runAsync(Runnable runnable);

}
