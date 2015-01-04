package com.kosakorner.kosakore.bukkit.inventory;

import com.kosakorner.kosakore.api.inventory.IInventory;
import com.kosakorner.kosakore.api.item.IItemStack;
import com.kosakorner.kosakore.api.type.Type;
import org.bukkit.inventory.Inventory;

public class BukkitInventory implements IInventory {

    public Inventory backingInventory;

    public BukkitInventory(Inventory inventory) {
        backingInventory = inventory;
    }

    public boolean containsAtLeast(IItemStack stack, int amount) {
        return false;
    }

    public void remove(Type type) {

    }

    public void remove(IItemStack stack) {

    }

    public void clear(int index) {

    }

    public void clear() {

    }

}
