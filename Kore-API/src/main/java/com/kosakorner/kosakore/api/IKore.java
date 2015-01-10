package com.kosakorner.kosakore.api;

import com.kosakorner.kosakore.api.item.IItemFactory;
import com.kosakorner.kosakore.api.world.IWorld;
import com.kosakorner.kosakore.api.world.IWorldFactory;

public interface IKore {

    public IItemFactory itemFactory();

    public IWorldFactory worldFactory();

}
