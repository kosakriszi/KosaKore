package com.kosakorner.kosakore.api.item;

import com.kosakorner.kosakore.api.type.*;

public abstract class ItemFactory {

    // TODO: fully finish this for all items with damage possibilities
    public IItemStack parseItemStack(String type, int amount) {
        if (!type.contains(":")) {
            Type parsedType = Type.getType(type);
            if (parsedType.getMaxStackSize() == 1) {
                return createItemStack(Type.getType(type), 1);
            }
            else {
                return createItemStack(Type.getType(type), amount);
            }
        }
        else {
            String[] parts = type.split(":");
            Type material = Type.getType(parts[0]);

            try {
                short damage = (short) Integer.parseInt(parts[1]);
                return createItemStack(material, amount, damage);
            } catch (NumberFormatException e) {
                // Eat exception, move on.
            }

            if (parts[0].equals("MONSTER_EGG")) {
                return createItemStack(material, amount, SpawnEggType.get(parts[1]));
            }

            if (parts[0].equals("COAL")) {
                if (parts.length == 2) {
                    return createItemStack(material, amount, CoalType.get(parts[1]));
                } else {
                    return createItemStack(material, amount, (short) 0);
                }
            }

            if (parts[0].equals("LOG") ||
                parts[0].equals("SAPLING") ||
                parts[0].equals("LEAVES") ||
                parts[0].equals("WOOD") ||
                parts[0].equals("WOOD_STEP") ||
                parts[0].equals("WOOD_DOUBLE_STEP")) {
                if (parts[0].equals("LOG") && parts[1].equals("ACACIA") || parts[1].equals("DARK_OAK")) {
                    return createItemStack(Type.getType("LOG_2"), amount, (short) (TreeType.get(parts[1]).getDamage() - 4));
                }
                else if (parts[0].equals("LEAVES") && parts[1].equals("ACACIA") || parts[1].equals("DARK_OAK")) {
                    return createItemStack(Type.getType("LEAVES_2"), amount, (short) (TreeType.get(parts[1]).getDamage() - 4));
                }
                else {
                    return createItemStack(material, amount, TreeType.get(parts[1]));
                }
            }

            if (parts[0].equals("DYE")) {
                return createItemStack(Type.getType("INK_SACK"), amount, ColorType.get(parts[1]));
            }

            if (parts[0].equals("WOOL") ||
                parts[0].equals("CARPET") ||
                parts[0].equals("STAINED_GLASS") ||
                parts[0].equals("STAINED_CLAY") ||
                parts[0].equals("STAINED_GLASS_PANE")) {
                return createItemStack(material, amount, (short) (15 - ColorType.get(parts[1]).getDamage()));
            }

            if (parts[0].equals("RAW_FISH") ||
                parts[0].equals("COOKED_FISH")) {
                return createItemStack(material, amount, FishType.get(parts[1]).getDamage());
            }

            if (parts[0].equals("POTION")) {
                return createItemStack(material, amount, PotionType.get(parts[1]).getDamage());
            }

        }
        return null;
    }

    public abstract IItemStack createItemStack(Type type, int amount);

    public abstract IItemStack createItemStack(Type type, int amount, IDamageHelper damage);

    public abstract IItemStack createItemStack(Type type, int amount, short damage);

}
