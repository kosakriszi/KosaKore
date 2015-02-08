package com.kosakorner.kosakore.api;

import com.kosakorner.kosakore.api.entity.IPlayer;
import com.kosakorner.kosakore.api.item.IItemFactory;
import com.kosakorner.kosakore.api.module.ModuleLoader;
import com.kosakorner.kosakore.api.world.IWorldFactory;

import java.io.File;
import java.util.List;
import java.util.UUID;

public interface IKore {

    public ModuleLoader getModuleLoader();

    public File getWorkingDir();

    public IPlayer getPlayer(UUID uuid);

    public List<IPlayer> getPlayers();

    public IItemFactory itemFactory();

    public IWorldFactory worldFactory();

}
