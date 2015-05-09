package com.kosakorner.kosakore.bukkit.inventory;

import com.kosakorner.kosakore.api.inventory.IInventory;
import com.kosakorner.kosakore.api.item.IItemStack;
import com.kosakorner.kosakore.api.type.Type;
import com.kosakorner.kosakore.bukkit.util.ConversionUtils;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

public class BukkitInventory implements IInventory {

    public Inventory backingInventory;

    public BukkitInventory(Inventory inventory) {
        backingInventory = inventory;
    }

    public void add(IItemStack stack) {
        backingInventory.addItem(ConversionUtils.fromKoreItemStack(stack));
    }

    public boolean containsAtLeast(IItemStack stack, int amount) {
        return backingInventory.containsAtLeast(ConversionUtils.fromKoreItemStack(stack), amount);
    }

    public void remove(Type type) {
        backingInventory.remove(Material.getMaterial(type.name()));
    }

    public void remove(IItemStack stack) {
        backingInventory.remove(ConversionUtils.fromKoreItemStack(stack));
    }

    public void clear(int index) {
        backingInventory.clear(index);
    }

    public void clear() {
        backingInventory.clear();
    }

}
