package com.kosakorner.kosakore.api.enchantment;

import com.kosakorner.kosakore.api.type.EnchantmentType;

public interface IEnchantment {

    EnchantmentType getType();

    int getLevel();

}
