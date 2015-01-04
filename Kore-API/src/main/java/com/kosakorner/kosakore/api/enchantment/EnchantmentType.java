package com.kosakorner.kosakore.api.enchantment;

public enum EnchantmentType {

    PROTECTION("protection", 4),
    FIRE_PROTECTION("fireProtection", 4),
    FEATHER_FALLING("featherFalling", 4),
    BLAST_PROTECTION("blastProtection", 4),
    PROJECTILE_PROTECTION("projectileProtection", 4),
    RESPIRATION("respiration", 3),
    AQUA_AFFINITY("aquaAffinity", 1),
    THORNS("thorns", 3),
    DEPTH_STRIDER("depthStrider", 3),
    SHARPNESS("sharpness", 5),
    SMITE("smite", 5),
    BANE_OF_ARTHROPODS("baneOfArthropods", 5),
    KNOCKBACK("knockback", 2),
    FIRE_ASPECT("fireAspect", 2),
    LOOTING("looting", 3),
    POWER("power", 5),
    PUNCH("punch", 2),
    FLAME("flame", 1),
    INFINITY("infinity", 1),
    EFFICIENCY("efficiency", 5),
    SILK_TOUCH("silkTouch", 1),
    UNBREAKING("unbreaking", 3),
    FORTUNE("fortune", 3),
    LUCK_OF_THE_SEA("luckOfTheSea", 3),
    LURE("lure", 3);

    private String name;
    private int maxLevel;

    private EnchantmentType(String name, int maxLevel) {
        this.name = name;
        this.maxLevel = maxLevel;
    }

    public String getName() {
        return name;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

}
