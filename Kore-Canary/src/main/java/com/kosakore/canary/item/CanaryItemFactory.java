package com.kosakore.canary.item;

import com.kosakorner.kosakore.api.item.IItemStack;
import com.kosakorner.kosakore.api.item.ItemFactory;
import com.kosakorner.kosakore.api.type.IDamageHelper;
import com.kosakorner.kosakore.api.type.Type;

public class CanaryItemFactory extends ItemFactory {

    public IItemStack createItemStack(Type type, int amount) {
        return new CanaryItemStack(type, amount);
    }

    public IItemStack createItemStack(Type type, int amount, IDamageHelper damage) {
        return new CanaryItemStack(type, amount, damage);
    }

    public IItemStack createItemStack(Type type, int amount, short damage) {
        return new CanaryItemStack(type, amount, damage);
    }

}
