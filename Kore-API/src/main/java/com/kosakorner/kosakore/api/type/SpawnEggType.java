package com.kosakorner.kosakore.api.type;

import java.util.HashMap;
import java.util.Map;

public enum SpawnEggType implements IDamageHelper {

    CREEPER(50),
    SKELETON(51),
    SPIDER(52),
    ZOMBIE(54),
    SLIME(55),
    GHAST(56),
    ZOMBIE_PIGMAN(57),
    ENDERMAN(58),
    CAVE_SPIDER(59),
    SILVERFISH(60),
    BLAZE(61),
    MAGMA_CUBE(62),
    BAT(65),
    WITCH(66),
    ENDERMITE(67),
    GUARDIAN(68),
    PIG(90),
    SHEEP(91),
    COW(92),
    CHICKEN(93),
    SQUID(94),
    WOLF(95),
    MOOSHROOM(96),
    OCELOT(98),
    HORSE(100),
    RABBIT(101),
    VILLAGER(120);

    private int damage;
    private static Map<String, SpawnEggType> map = new HashMap<String, SpawnEggType>();

    static {
        for (SpawnEggType entry : SpawnEggType.values()) {
            map.put(entry.name(), entry);
        }
    }

    SpawnEggType(int damage) {
        this.damage = damage;
    }

    public short getDamage() {
        return (short) damage;
    }

    public static SpawnEggType get(String name) {
        return map.get(name);
    }

}
