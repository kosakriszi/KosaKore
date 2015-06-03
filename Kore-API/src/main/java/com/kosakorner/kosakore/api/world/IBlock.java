package com.kosakorner.kosakore.api.world;

import com.kosakorner.kosakore.api.inventory.IInventory;
import com.kosakorner.kosakore.api.type.Type;

public interface IBlock {

    Type getType();

    void setType(Type type);

    IInventory getInventory();

}
