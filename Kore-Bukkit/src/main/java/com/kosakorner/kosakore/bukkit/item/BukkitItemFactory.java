package com.kosakorner.kosakore.bukkit.item;

import com.kosakorner.kosakore.api.item.IItemFactory;
import com.kosakorner.kosakore.api.item.IItemStack;
import com.kosakorner.kosakore.api.type.*;

public class BukkitItemFactory implements IItemFactory {

    // TODO: fully finish this for all items with damage possibilities
    public IItemStack parseItemStack(String type, int amount) {
        if (!type.contains(":")) {
            Type parsedType = Type.getType(type);
            if (parsedType.getMaxStackSize() == 1) {
                return new BukkitItemStack(Type.getType(type), 1);
            }
            else {
                return new BukkitItemStack(Type.getType(type), amount);
            }
        }
        else {
            String[] parts = type.split(":");
            Type material = Type.getType(parts[0]);

            if (parts[0].equals("MONSTER_EGG")) {
                return new BukkitItemStack(material, amount, SpawnEggType.get(parts[1]).getDamage());
            }

            if (parts[0].equals("LOG") ||
                parts[0].equals("SAPLING") ||
                parts[0].equals("LEAVES") ||
                parts[0].equals("WOOD") ||
                parts[0].equals("WOOD_STEP") ||
                parts[0].equals("WOOD_DOUBLE_STEP")) {
                if (parts[0].equals("LOG") && parts[1].equals("ACACIA") || parts[1].equals("DARK_OAK")) {
                    return new BukkitItemStack(Type.getType("LOG_2"), amount, (short) (TreeType.get(parts[1]).getDamage() - 4));
                }
                else if (parts[0].equals("LEAVES") && parts[1].equals("ACACIA") || parts[1].equals("DARK_OAK")) {
                    return new BukkitItemStack(Type.getType("LEAVES_2"), amount, (short) (TreeType.get(parts[1]).getDamage() - 4));
                }
                else {
                    return new BukkitItemStack(material, amount, TreeType.get(parts[1]).getDamage());
                }
            }

            if (parts[0].equals("DYE")) {
                return new BukkitItemStack(Type.getType("INK_SACK"), amount, ColorType.get(parts[1]).getDamage());
            }

            if (parts[0].equals("WOOL") ||
                parts[0].equals("CARPET") ||
                parts[0].equals("STAINED_GLASS") ||
                parts[0].equals("STAINED_CLAY") ||
                parts[0].equals("STAINED_GLASS_PANE")) {
                return new BukkitItemStack(material, amount, (short) (15 - ColorType.get(parts[1]).getDamage()));
            }

            if (parts[0].equals("RAW_FISH") ||
                parts[0].equals("COOKED_FISH")) {
                return new BukkitItemStack(material, amount, FishType.get(parts[1]).getDamage());
            }

            if (parts[0].equals("POTION")) {
                return new BukkitItemStack(material, amount, PotionType.get(parts[1]).getDamage());
            }

        }
        return null;
    }

    public IItemStack createItemStack(Type type, int amount) {
        return new BukkitItemStack(type, amount);
    }

    public IItemStack createItemStack(Type type, int amount, IDamageHelper damage) {
        return new BukkitItemStack(type, amount, damage);
    }
}
