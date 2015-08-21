package com.kosakorner.kosakore.api.type;

import java.util.HashMap;
import java.util.Map;

public enum CoalType implements IDamageHelper {

    COAL(0),
    CHARCOAL(1);

    private int damage;
    private static Map<String, CoalType> map = new HashMap<>();

    static {
        for (CoalType entry : CoalType.values()) {
            map.put(entry.name(), entry);
        }
    }

    CoalType(int damage) {
        this.damage = damage;
    }

    public short getDamage() {
        return (short) damage;
    }

    public static CoalType get(String name) {
        return map.get(name);
    }

}