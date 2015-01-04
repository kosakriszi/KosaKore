package com.kosakorner.kosakore.api.item;

import com.kosakorner.kosakore.api.type.IDamageHelper;
import com.kosakorner.kosakore.api.type.Type;

public interface IItemFactory {

    public IItemStack createItemStack(Type type, int amount);

    public IItemStack createItemStack(Type type, int amount, IDamageHelper damage);

}
