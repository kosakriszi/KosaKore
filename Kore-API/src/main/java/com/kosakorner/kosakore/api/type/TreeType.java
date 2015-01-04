package com.kosakorner.kosakore.api.type;

import java.util.HashMap;
import java.util.Map;

public enum TreeType implements IDamageHelper {

    OAK(0),
    SPRUCE(1),
    BIRCH(2),
    JUNGLE(3),
    ACACIA(4),
    DARK_OAK(5);

    private int damage;
    private static Map<String, TreeType> map = new HashMap<String, TreeType>();

    static {
        for (TreeType entry : TreeType.values()) {
            map.put(entry.name(), entry);
        }
    }

    private TreeType(int damage) {
        this.damage = damage;
    }

    public short getDamage() {
        return (short) damage;
    }

    public static TreeType get(String name) {
        return map.get(name);
    }

}
