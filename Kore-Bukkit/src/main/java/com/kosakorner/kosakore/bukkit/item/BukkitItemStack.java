package com.kosakorner.kosakore.bukkit.item;

import com.kosakorner.kosakore.api.enchantment.IEnchantment;
import com.kosakorner.kosakore.api.item.IItemStack;
import com.kosakorner.kosakore.api.type.IDamageHelper;
import com.kosakorner.kosakore.api.type.Type;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BukkitItemStack implements IItemStack {

    private ItemStack backingItem;

    public BukkitItemStack(Type type, int amount) {
        this(type, amount, (short) 0);
    }

    public BukkitItemStack(Type type, int amount, IDamageHelper damage) {
        this(type, amount, damage.getDamage());
    }

    public BukkitItemStack(Type type, int amount, short damage) {

        backingItem = new ItemStack(Material.getMaterial(type.name()), amount, damage);
    }

    public BukkitItemStack(ItemStack backingItem) {
        this.backingItem = backingItem;
    }

    public String getName() {
        if (backingItem.hasItemMeta() && backingItem.getItemMeta().hasLore()) {
            return backingItem.getItemMeta().getDisplayName();
        }
        else {
            return backingItem.getType().name().replace("_", " ").toLowerCase();
        }
    }

    public List<String> getLore() {
        if (backingItem.hasItemMeta() && backingItem.getItemMeta().hasLore()) {
            return backingItem.getItemMeta().getLore();
        }
        else {
            return new ArrayList<>();
        }
    }

    public Type getType() {
        return Type.getType(backingItem.getType().name());
    }

    public int getStackSize() {
        return backingItem.getAmount();
    }

    public void setStackSize(int amount) {
        backingItem.setAmount(amount);
    }

    public short getDurability() {
        return backingItem.getDurability();
    }

    public List<IEnchantment> getEnchantments() {
        return null;
    }

    public ItemStack getBackingItem() {
        return backingItem;
    }
}
