package com.kosakorner.kosakore.bukkit.item;

import com.kosakorner.kosakore.api.item.ItemFactory;
import com.kosakorner.kosakore.api.item.IItemStack;
import com.kosakorner.kosakore.api.type.*;

public class BukkitItemFactory extends ItemFactory {

    public IItemStack createItemStack(Type type, int amount) {
        return new BukkitItemStack(type, amount);
    }

    public IItemStack createItemStack(Type type, int amount, IDamageHelper damage) {
        return new BukkitItemStack(type, amount, damage);
    }

    public IItemStack createItemStack(Type type, int amount, short damage) {
        return new BukkitItemStack(type, amount, damage);
    }

}
