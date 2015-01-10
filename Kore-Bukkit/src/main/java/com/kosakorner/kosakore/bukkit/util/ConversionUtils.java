package com.kosakorner.kosakore.bukkit.util;

import com.kosakorner.kosakore.api.item.IItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ConversionUtils {

    // TODO: add enchantment conversions
    public static ItemStack fromKoreItemStack(IItemStack stack) {
        ItemStack toReturn = new ItemStack(Material.getMaterial(stack.getType().name()), stack.getStackSize(), stack.getDurability());
        ItemMeta meta = toReturn.getItemMeta();
        meta.setDisplayName(stack.getName());
        toReturn.setItemMeta(meta);
        return toReturn;
    }

}
