package com.kosakorner.kosakore.api.inventory;

import com.kosakorner.kosakore.api.item.IItemStack;
import com.kosakorner.kosakore.api.type.Type;

public interface IInventory {

    void add(IItemStack stack);

    boolean containsAtLeast(IItemStack stack, int amount);

    void remove(Type type);

    void remove(IItemStack stack);

    void clear(int index);

    void clear();

}
