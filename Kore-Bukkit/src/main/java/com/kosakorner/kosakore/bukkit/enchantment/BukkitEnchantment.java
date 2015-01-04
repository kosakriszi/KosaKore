package com.kosakorner.kosakore.bukkit.enchantment;

import com.kosakorner.kosakore.api.enchantment.EnchantmentType;
import com.kosakorner.kosakore.api.enchantment.IEnchantment;

public class BukkitEnchantment implements IEnchantment {

    private EnchantmentType type;
    private int             level;

    public BukkitEnchantment(EnchantmentType type, int level) {
        this.type = type;
        this.level = level;
    }

    public EnchantmentType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

}
