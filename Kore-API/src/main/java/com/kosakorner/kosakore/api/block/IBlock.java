package com.kosakorner.kosakore.api.block;

import com.kosakorner.kosakore.api.inventory.IInventory;
import com.kosakorner.kosakore.api.type.Type;

public interface IBlock {

    public Type getType();

    public void setType(Type type);

    public IInventory getInventory();

}
