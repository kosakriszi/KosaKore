package com.kosakore.canary.item;

import com.kosakorner.kosakore.api.enchantment.IEnchantment;
import com.kosakorner.kosakore.api.item.IItemStack;
import com.kosakorner.kosakore.api.type.IDamageHelper;
import com.kosakorner.kosakore.api.type.Type;
import net.canarymod.Canary;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;

import java.util.Arrays;
import java.util.List;

public class CanaryItemStack implements IItemStack {

    private Item backingItem;

    public CanaryItemStack(Type type, int amount) {
        this(type, amount, (short) 0);
    }

    public CanaryItemStack(Type type, int amount, IDamageHelper damage) {
        this(type, amount, damage.getDamage());
    }

    public CanaryItemStack(Type type, int amount, short damage) {
        backingItem = Canary.factory().getItemFactory().newItem(ItemType.fromString(type.name()), damage, amount);
    }

    public CanaryItemStack(Item backingItem) {
        this.backingItem = backingItem;
    }

    public String getName() {
        return backingItem.getDisplayName();
    }

    public List<String> getLore() {
        return Arrays.asList(backingItem.getLore());
    }

    public Type getType() {
        return Type.getType(backingItem.getType().getMachineName().split(":")[1]);
    }

    public int getStackSize() {
        return backingItem.getAmount();
    }

    public void setStackSize(int amount) {
        backingItem.setAmount(amount);
    }

    public short getDurability() {
        return (short) backingItem.getDamage();
    }

    public List<IEnchantment> getEnchantments() {
        return null;
    }

    public Item getBackingItem() {
        return backingItem;
    }

}
