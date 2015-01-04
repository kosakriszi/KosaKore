package com.kosakorner.kosakore.api.inventory;

import com.kosakorner.kosakore.api.item.IItemStack;
import com.kosakorner.kosakore.api.type.Type;

public interface IInventory {

    public boolean containsAtLeast(IItemStack stack, int amount);

    public void remove(Type type);

    public void remove(IItemStack stack);

    public void clear(int index);

    public void clear();

}
