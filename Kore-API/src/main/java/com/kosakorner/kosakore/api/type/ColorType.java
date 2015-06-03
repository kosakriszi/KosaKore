package com.kosakorner.kosakore.api.type;

import java.util.HashMap;
import java.util.Map;

public enum ColorType implements IDamageHelper {

    BLACK(0),
    RED(1),
    GREEN(2),
    BROWN(3),
    BLUE(4),
    PURPLE(5),
    CYAN(6),
    LIGHT_GRAY(7),
    GRAY(8),
    PINK(9),
    LIME(10),
    YELLOW(11),
    LIGHT_BLUE(12),
    MAGENTA(13),
    ORANGE(14),
    WHITE(15);

    private int damage;
    private static Map<String, ColorType> map = new HashMap<String, ColorType>();

    static {
        for (ColorType entry : ColorType.values()) {
            map.put(entry.name(), entry);
        }
    }

    ColorType(int damage) {
        this.damage = damage;
    }

    public short getDamage() {
        return (short) damage;
    }

    public static ColorType get(String name) {
        return map.get(name);
    }

}
