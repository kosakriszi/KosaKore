package com.kosakorner.kosakore.api.type;

import java.util.HashMap;
import java.util.Map;

public enum FishType implements IDamageHelper {

    NORMAL(0),
    SALMON(1),
    CLOWN_FISH(2),
    PUFFER_FISH(3);

    private int damage;
    private static Map<String, FishType> map = new HashMap<String, FishType>();

    static {
        for (FishType entry : FishType.values()) {
            map.put(entry.name(), entry);
        }
    }

    private FishType(int damage) {
        this.damage = damage;
    }

    public short getDamage() {
        return (short) damage;
    }

    public static FishType get(String name) {
        return map.get(name);
    }

}