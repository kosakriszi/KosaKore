package com.kosakorner.kosakore.api.item;

import com.kosakorner.kosakore.api.enchantment.IEnchantment;
import com.kosakorner.kosakore.api.type.Type;

import java.util.List;

public interface IItemStack {

    String getName();

    List<String> getLore();

    Type getType();

    int getStackSize();

    void setStackSize(int amount);

    short getDurability();

    List<IEnchantment> getEnchantments();

}
