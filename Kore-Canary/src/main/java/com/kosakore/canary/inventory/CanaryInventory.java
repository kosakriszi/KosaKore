package com.kosakore.canary.inventory;

import com.kosakore.canary.util.ConversionUtils;
import com.kosakorner.kosakore.api.inventory.IInventory;
import com.kosakorner.kosakore.api.item.IItemStack;
import com.kosakorner.kosakore.api.type.Type;
import net.canarymod.Canary;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;

public class CanaryInventory implements IInventory {

    public Inventory backingInventory;

    public CanaryInventory(Inventory inventory) {
        backingInventory = inventory;
    }

    public void add(IItemStack stack) {
        backingInventory.addItem(ConversionUtils.fromKoreItemStack(stack));
    }

    public boolean containsAtLeast(IItemStack stack, int amount) {
        int found = 0;
        Item target = ConversionUtils.fromKoreItemStack(stack);
        Item[] contents = backingInventory.getContents();
        for (Item item : contents) {
            if (item.getType().equals(target.getType())) {
                found += item.getAmount();
            }
        }
        return found >= amount;
    }

    public void remove(Type type) {
        backingInventory.removeItem(ItemType.fromString(type.name()));
    }

    public void remove(IItemStack stack) {
        backingInventory.removeItem(ConversionUtils.fromKoreItemStack(stack));
    }

    public void clear(int index) {
        backingInventory.setSlot(index, Canary.factory().getItemFactory().newItem(0));
    }

    public void clear() {
        backingInventory.clearContents();
    }

}
