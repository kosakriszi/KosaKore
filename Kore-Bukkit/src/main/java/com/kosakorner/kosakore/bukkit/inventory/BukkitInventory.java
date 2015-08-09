package com.kosakorner.kosakore.bukkit.inventory;

import com.kosakorner.kosakore.api.inventory.IInventory;
import com.kosakorner.kosakore.api.item.IItemStack;
import com.kosakorner.kosakore.api.type.Type;
import com.kosakorner.kosakore.bukkit.util.ConversionUtils;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BukkitInventory implements IInventory {

    public Inventory backingInventory;

    public BukkitInventory(Inventory inventory) {
        backingInventory = inventory;
    }

    public List<IItemStack> add(IItemStack stack) {
        List<IItemStack> toReturn = new ArrayList<>();
        HashMap<Integer, ItemStack> stacks = backingInventory.addItem(ConversionUtils.fromKoreItemStack(stack));
        for (ItemStack itemStack : stacks.values()) {
            toReturn.add(ConversionUtils.toKoreItemStack(itemStack));
        }
        return toReturn;
    }

    public boolean containsAtLeast(IItemStack stack, int amount) {
        return backingInventory.containsAtLeast(ConversionUtils.fromKoreItemStack(stack), amount);
    }

    public void remove(Type type) {
        backingInventory.remove(Material.getMaterial(type.name()));
    }

    public void remove(IItemStack stack) {
        backingInventory.removeItem(ConversionUtils.fromKoreItemStack(stack));
    }

    public void clear(int index) {
        backingInventory.clear(index);
    }

    public void clear() {
        backingInventory.clear();
    }

}
