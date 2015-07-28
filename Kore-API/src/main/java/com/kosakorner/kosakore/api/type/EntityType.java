package com.kosakorner.kosakore.api.type;

import java.util.HashMap;
import java.util.Map;

public enum EntityType {

    DROPPED_ITEM(1),
    EXPERIENCE_ORB(2),
    LEASH_HITCH(8),
    PAINTING(9),
    ARROW(10),
    SNOWBALL(11),
    FIREBALL(12),
    SMALL_FIREBALL(13),
    ENDER_PEARL(14),
    ENDER_SIGNAL(15),
    THROWN_EXP_BOTTLE(17),
    ITEM_FRAME(18),
    WITHER_SKULL(19),
    PRIMED_TNT(20),
    FALLING_BLOCK(21),
    FIREWORK(22),
    ARMOR_STAND(30),
    MINECART_COMMAND(40),
    BOAT(41),
    MINECART(42),
    MINECART_CHEST(43),
    MINECART_FURNACE(44),
    MINECART_TNT(45),
    MINECART_HOPPER(46),
    MINECART_MOB_SPAWNER(47),
    CREEPER(50),
    SKELETON(51),
    SPIDER(52),
    GIANT(53),
    ZOMBIE(54),
    SLIME(55),
    GHAST(56),
    PIG_ZOMBIE(57),
    ENDERMAN(58),
    CAVE_SPIDER(59),
    SILVERFISH(60),
    BLAZE(61),
    MAGMA_CUBE(62),
    ENDER_DRAGON(63),
    WITHER(64),
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
    MUSHROOM_COW(96),
    SNOWMAN(97),
    OCELOT(98),
    IRON_GOLEM(99),
    HORSE(100),
    RABBIT(101),
    VILLAGER(120),
    ENDER_CRYSTAL(200),
    SPLASH_POTION(-1),
    EGG(-1),
    FISHING_HOOK(-1),
    LIGHTNING(-1),
    WEATHER(-1),
    PLAYER(-1),
    COMPLEX_PART(-1),
    UNKNOWN(-1);

    private int id;
    private static Map<String, EntityType> nameMap = new HashMap<>();
    private static Map<Short, EntityType>  idMap   = new HashMap<>();

    static {
        for (EntityType entry : EntityType.values()) {
            nameMap.put(entry.name(), entry);
            idMap.put(entry.getId(), entry);
        }
    }

    EntityType(int id) {
        this.id = id;
    }

    public short getId() {
        return (short) id;
    }

    public static EntityType get(String name) {
        return nameMap.get(name);
    }

    public static EntityType get(short id) {
        return idMap.get(id);
    }

}
