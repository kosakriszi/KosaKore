package com.kosakorner.kosakore.api.item;

import com.kosakorner.kosakore.api.enchantment.IEnchantment;
import com.kosakorner.kosakore.api.type.Type;

import java.util.List;

public interface IItemStack {

    public String getName();

    public List<String> getLore();

    public Type getType();

    public int getStackSize();

    public int getDurability();

    public List<IEnchantment> getEnchantments();

}
