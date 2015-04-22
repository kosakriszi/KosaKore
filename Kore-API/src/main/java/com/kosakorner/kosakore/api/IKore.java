package com.kosakorner.kosakore.api;

import com.kosakorner.kosakore.api.adapter.Adapters;
import com.kosakorner.kosakore.api.adapter.IAdapter;
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

    public List<IPlayer> getPlayers();

    public ItemFactory itemFactory();

    public IWorldFactory worldFactory();

    public EventBus getEventBus();

    public HandlerRegistry getHandlerRegistry();

    public ModuleLoader getModuleLoader();

    public IAdapter getAdapter(Adapters type);

    public File getWorkingDir();

}
