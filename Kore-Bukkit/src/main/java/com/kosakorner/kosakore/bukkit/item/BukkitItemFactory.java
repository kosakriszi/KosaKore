package com.kosakorner.kosakore.bukkit.item;

import com.kosakorner.kosakore.api.item.IItemFactory;
import com.kosakorner.kosakore.api.item.IItemStack;
import com.kosakorner.kosakore.api.type.IDamageHelper;
import com.kosakorner.kosakore.api.type.Type;

public class BukkitItemFactory implements IItemFactory {

    public IItemStack createItemStack(Type type, int amount) {
        return new BukkitItemStack(type, amount);
    }

    public IItemStack createItemStack(Type type, int amount, IDamageHelper damage) {
        return new BukkitItemStack(type, amount, damage);
    }
}
