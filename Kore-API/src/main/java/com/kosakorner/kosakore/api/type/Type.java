package com.kosakorner.kosakore.api.type;

import java.util.HashMap;
import java.util.Map;

public enum Type {
    AIR(0),
    STONE,
    GRASS,
    DIRT,
    COBBLESTONE,
    WOOD,
    SAPLING,
    BEDROCK,
    WATER,
    STATIONARY_WATER,
    LAVA,
    STATIONARY_LAVA,
    SAND,
    GRAVEL,
    GOLD_ORE,
    IRON_ORE,
    COAL_ORE,
    LOG,
    LEAVES,
    SPONGE,
    GLASS,
    LAPIS_ORE,
    LAPIS_BLOCK,
    DISPENSER,
    SANDSTONE,
    NOTE_BLOCK,
    BED_BLOCK,
    POWERED_RAIL,
    DETECTOR_RAIL,
    PISTON_STICKY_BASE,
    WEB,
    LONG_GRASS,
    DEAD_BUSH,
    PISTON_BASE,
    PISTON_EXTENSION,
    WOOL,
    PISTON_MOVING_PIECE,
    YELLOW_FLOWER,
    RED_ROSE,
    BROWN_MUSHROOM,
    RED_MUSHROOM,
    GOLD_BLOCK,
    IRON_BLOCK,
    DOUBLE_STEP,
    STEP,
    BRICK,
    TNT,
    BOOKSHELF,
    MOSSY_COBBLESTONE,
    OBSIDIAN,
    TORCH,
    FIRE,
    MOB_SPAWNER,
    WOOD_STAIRS,
    CHEST,
    REDSTONE_WIRE,
    DIAMOND_ORE,
    DIAMOND_BLOCK,
    WORKBENCH,
    CROPS,
    SOIL,
    FURNACE,
    BURNING_FURNACE,
    SIGN_POST,
    WOODEN_DOOR,
    LADDER,
    RAILS,
    COBBLESTONE_STAIRS,
    WALL_SIGN,
    LEVER,
    STONE_PLATE,
    IRON_DOOR_BLOCK,
    WOOD_PLATE,
    REDSTONE_ORE,
    GLOWING_REDSTONE_ORE,
    REDSTONE_TORCH_OFF,
    REDSTONE_TORCH_ON,
    STONE_BUTTON,
    SNOW,
    ICE,
    SNOW_BLOCK,
    CACTUS,
    CLAY,
    SUGAR_CANE_BLOCK,
    JUKEBOX,
    FENCE,
    PUMPKIN,
    NETHERRACK,
    SOUL_SAND,
    GLOWSTONE,
    PORTAL,
    JACK_O_LANTERN,
    CAKE_BLOCK,
    DIODE_BLOCK_OFF,
    DIODE_BLOCK_ON,
    STAINED_GLASS,
    TRAP_DOOR,
    MONSTER_EGGS,
    SMOOTH_BRICK,
    HUGE_MUSHROOM_1,
    HUGE_MUSHROOM_2,
    IRON_FENCE,
    THIN_GLASS,
    MELON_BLOCK,
    PUMPKIN_STEM,
    MELON_STEM,
    VINE,
    FENCE_GATE,
    BRICK_STAIRS,
    SMOOTH_STAIRS,
    MYCEL,
    WATER_LILY,
    NETHER_BRICK,
    NETHER_FENCE,
    NETHER_BRICK_STAIRS,
    NETHER_WARTS,
    ENCHANTMENT_TABLE,
    BREWING_STAND,
    CAULDRON,
    ENDER_PORTAL,
    ENDER_PORTAL_FRAME,
    ENDER_STONE,
    DRAGON_EGG,
    REDSTONE_LAMP_OFF,
    REDSTONE_LAMP_ON,
    WOOD_DOUBLE_STEP,
    WOOD_STEP,
    COCOA,
    SANDSTONE_STAIRS,
    EMERALD_ORE,
    ENDER_CHEST,
    TRIPWIRE_HOOK,
    TRIPWIRE,
    EMERALD_BLOCK,
    SPRUCE_WOOD_STAIRS,
    BIRCH_WOOD_STAIRS,
    JUNGLE_WOOD_STAIRS,
    COMMAND,
    BEACON,
    COBBLE_WALL,
    FLOWER_POT,
    CARROT,
    POTATO,
    WOOD_BUTTON,
    SKULL,
    ANVIL,
    TRAPPED_CHEST,
    GOLD_PLATE,
    IRON_PLATE,
    REDSTONE_COMPARATOR_OFF,
    REDSTONE_COMPARATOR_ON,
    DAYLIGHT_DETECTOR,
    REDSTONE_BLOCK,
    QUARTZ_ORE,
    HOPPER,
    QUARTZ_BLOCK,
    QUARTZ_STAIRS,
    ACTIVATOR_RAIL,
    DROPPER,
    STAINED_CLAY,
    STAINED_GLASS_PANE,
    LEAVES_2,
    LOG_2,
    ACACIA_STAIRS,
    DARK_OAK_STAIRS,
    SLIME_BLOCK,
    BARRIER,
    IRON_TRAPDOOR,
    PRISMARINE,
    SEA_LANTERN,
    HAY_BLOCK,
    CARPET,
    HARD_CLAY,
    COAL_BLOCK,
    PACKED_ICE,
    DOUBLE_PLANT,
    STANDING_BANNER,
    WALL_BANNER,
    DAYLIGHT_DETECTOR_INVERTED,
    RED_SANDSTONE,
    RED_SANDSTONE_STAIRS,
    DOUBLE_STONE_SLAB2,
    STONE_SLAB2,
    SPRUCE_FENCE_GATE,
    BIRCH_FENCE_GATE,
    JUNGLE_FENCE_GATE,
    DARK_OAK_FENCE_GATE,
    ACACIA_FENCE_GATE,
    SPRUCE_FENCE,
    BIRCH_FENCE,
    JUNGLE_FENCE,
    DARK_OAK_FENCE,
    ACACIA_FENCE,
    SPRUCE_DOOR,
    BIRCH_DOOR,
    JUNGLE_DOOR,
    ACACIA_DOOR,
    DARK_OAK_DOOR,
    // ---- ITEMS START HERE ----
    IRON_SPADE(1, 250),
    IRON_PICKAXE(1, 250),
    IRON_AXE(1, 250),
    FLINT_AND_STEEL(1, 64),
    APPLE,
    BOW(1, 384),
    ARROW,
    COAL,
    DIAMOND,
    IRON_INGOT,
    GOLD_INGOT,
    IRON_SWORD(1, 250),
    WOOD_SWORD(1, 59),
    WOOD_SPADE(1, 59),
    WOOD_PICKAXE(1, 59),
    WOOD_AXE(1, 59),
    STONE_SWORD(1, 131),
    STONE_SPADE(1, 131),
    STONE_PICKAXE(1, 131),
    STONE_AXE(1, 131),
    DIAMOND_SWORD(1, 1561),
    DIAMOND_SPADE(1, 1561),
    DIAMOND_PICKAXE(1, 1561),
    DIAMOND_AXE(1, 1561),
    STICK,
    BOWL,
    MUSHROOM_SOUP(1),
    GOLD_SWORD(1, 32),
    GOLD_SPADE(1, 32),
    GOLD_PICKAXE(1, 32),
    GOLD_AXE(1, 32),
    STRING,
    FEATHER,
    SULPHUR,
    WOOD_HOE(1, 59),
    STONE_HOE(1, 131),
    IRON_HOE(1, 250),
    DIAMOND_HOE(1, 1561),
    GOLD_HOE(1, 32),
    SEEDS,
    WHEAT,
    BREAD,
    LEATHER_HELMET(1, 55),
    LEATHER_CHESTPLATE(1, 80),
    LEATHER_LEGGINGS(1, 75),
    LEATHER_BOOTS(1, 65),
    CHAINMAIL_HELMET(1, 165),
    CHAINMAIL_CHESTPLATE(1, 240),
    CHAINMAIL_LEGGINSG(1, 225),
    CHAINMAIL_BOOTS(1, 195),
    IRON_HELMET(1, 165),
    IRON_CHESTPLATE(1, 240),
    IRON_LEGGINGS(1, 225),
    IRON_BOOTS(1, 195),
    DIAMOND_HELMET(1, 363),
    DIAMOND_CHESTPLATE(1, 528),
    DIAMOND_LEGGINGS(1, 495),
    DIAMOND_BOOTS(1, 429),
    GOLD_HELMET(1, 77),
    GOLD_CHESTPLATE(1, 112),
    GOLD_LEGGINGS(1, 105),
    GOLD_BOOTS(1, 91),
    FLINT,
    PORK,
    GRILLED_PORK,
    PAINTING,
    GOLDEN_APPLE,
    SIGN(16),
    WOOD_DOOR,
    BUCKET(16),
    WATER_BUCKET(1),
    LAVA_BUCKET(1),
    MINECART(1),
    SADDLE(1),
    IRON_DOOR,
    REDSTONE,
    SNOW_BALL(16),
    BOAT(1),
    LEATHER,
    MILK_BUCKET(1),
    CLAY_BRICK,
    CLAY_BALL,
    SUGAR_CANE,
    PAPER,
    BOOK,
    SLIME_BALL,
    STORAGE_MINECART(1),
    POWERED_MINECART(1),
    EGG(16),
    COMPASS,
    FISHING_ROD(1, 64),
    WATCH,
    GLOWSTONE_DUST,
    RAW_FISH,
    COOKED_FISH,
    INK_SACK,
    BONE,
    SUGAR,
    CAKE,
    BED,
    DIODE,
    COOKIE,
    MAP,
    SHEARS(1, 238),
    MELON,
    PUMPKIN_SEEDS,
    MELON_SEEDS,
    RAW_BEEF,
    COOKED_BEEF,
    RAW_CHICKEN,
    COOKED_CHICKEN,
    ROTTEN_FLESH,
    ENDER_PEARL(16),
    BLAZE_ROD,
    CHAST_TEAR,
    GOLD_NUGGET,
    NETHER_STALK,
    POTION(1),
    GLASS_BOTTLE,
    SPIDER_EYE,
    FERMENTED_SPIDER_EYE,
    BLAZE_POWDER,
    MAGMA_CREAM,
    BREWING_STAND_ITEM,
    CAULDRON_ITEM,
    EYE_OF_ENDER,
    SPECKLED_MELON,
    MONSTER_EGG,
    EXP_BOTTLE,
    FIREBALL,
    BOOK_AND_QUILL(1),
    WRITTEN_BOOK(16),
    EMERALD,
    ITEM_FRAME,
    FLOWER_POT_ITEM,
    CARROT_ITEM,
    POTATO_ITEM,
    BAKED_POTATO,
    POISONOUS_POTATO,
    EMPTY_MAP,
    GOLDEN_CARROT,
    SKULL_ITEM,
    CARROT_STICK(1, 25),
    NETHER_STAR,
    PUMPKIN_PIE,
    FIREWORK,
    FIREWORK_CHARGE,
    ENCHANTED_BOOK(1),
    REDSTONE_COMPARATOR,
    NETHER_BRICK_ITEM,
    QUARTZ,
    EXPLOSIVE_MINECART(1),
    HOPPER_MINECART(1),
    PRISMARINE_SHARD,
    PRISMARINE_CRYSTALS,
    RABBIT,
    COOKED_RABBIT,
    RABBIT_STEW(1),
    RABBIT_FOOT,
    RABBIT_HIDE,
    ARMOR_STAND(16),
    IRON_BARDING(1),
    GOLD_BARDING(1),
    DIAMOND_BARDING(1),
    LEASH,
    NAME_TAG,
    COMMAND_MINECART(1),
    MUTTON,
    COOKED_MUTTON,
    BANNER(16),
    SPRUCE_DOOR_ITEM,
    BIRCH_DOOR_ITEM,
    JUNGLE_DOOR_ITEM,
    ACACIA_DOOR_ITEM,
    DARK_OAK_DOOR_ITEM,
    GOLD_RECORD(1),
    GREEN_RECORD(1),
    RECORD_3(1),
    RECORD_4(1),
    RECORD_5(1),
    RECORD_6(1),
    RECORD_7(1),
    RECORD_8(1),
    RECORD_9(1),
    RECORD_10(1),
    RECORD_11(1),
    RECORD_12(1);

    private static Map<String, Type> typeNames = new HashMap<String, Type>();

    private int maxStackSize;
    private int maxDurability;

    private Type() {
        this(64);
    }

    private Type(int maxStackSize) {
        this(maxStackSize, 0);
    }

    private Type(int maxStackSize, int maxDurability) {
        this.maxStackSize = maxStackSize;
        this.maxDurability = maxDurability;
    }

    public static Type getType(String name) {
        return typeNames.get(name);
    }

    public int getMaxStackSize() {
        return maxStackSize;
    }

    public int getMaxDurability() {
        return maxDurability;
    }

    public boolean isBlock() {
        return this.ordinal() < 256;
    }

    public boolean isEdible() {
        if (isBlock()) {
            return false;
        }
        switch (this) {
            case BREAD:
            case CARROT_ITEM:
            case BAKED_POTATO:
            case POTATO_ITEM:
            case POISONOUS_POTATO:
            case GOLDEN_CARROT:
            case PUMPKIN_PIE:
            case COOKIE:
            case MELON:
            case MUSHROOM_SOUP:
            case RAW_CHICKEN:
            case COOKED_CHICKEN:
            case RAW_BEEF:
            case COOKED_BEEF:
            case RAW_FISH:
            case COOKED_FISH:
            case PORK:
            case GRILLED_PORK:
            case APPLE:
            case GOLDEN_APPLE:
            case ROTTEN_FLESH:
            case SPIDER_EYE:
            case RABBIT:
            case COOKED_RABBIT:
            case RABBIT_STEW:
            case MUTTON:
            case COOKED_MUTTON:
                return true;
            default:
                return false;
        }
    }

    public boolean isRecord() {
        if (isBlock()) {
            return false;
        }
        switch (this) {
            case GOLD_RECORD:
            case GREEN_RECORD:
            case RECORD_3:
            case RECORD_4:
            case RECORD_5:
            case RECORD_6:
            case RECORD_7:
            case RECORD_8:
            case RECORD_9:
            case RECORD_10:
            case RECORD_11:
            case RECORD_12:
                return true;
            default:
                return false;
        }
    }

    public boolean isSolid() {
        if (!isBlock()) {
            return false;
        }
        switch (this) {
            case STONE:
            case GRASS:
            case DIRT:
            case COBBLESTONE:
            case WOOD:
            case BEDROCK:
            case SAND:
            case GRAVEL:
            case GOLD_ORE:
            case IRON_ORE:
            case COAL_ORE:
            case LOG:
            case LEAVES:
            case SPONGE:
            case GLASS:
            case LAPIS_ORE:
            case LAPIS_BLOCK:
            case DISPENSER:
            case SANDSTONE:
            case NOTE_BLOCK:
            case BED_BLOCK:
            case PISTON_STICKY_BASE:
            case PISTON_BASE:
            case PISTON_EXTENSION:
            case WOOL:
            case PISTON_MOVING_PIECE:
            case GOLD_BLOCK:
            case IRON_BLOCK:
            case DOUBLE_STEP:
            case STEP:
            case BRICK:
            case TNT:
            case BOOKSHELF:
            case MOSSY_COBBLESTONE:
            case OBSIDIAN:
            case MOB_SPAWNER:
            case WOOD_STAIRS:
            case CHEST:
            case DIAMOND_ORE:
            case DIAMOND_BLOCK:
            case WORKBENCH:
            case SOIL:
            case FURNACE:
            case BURNING_FURNACE:
            case SIGN_POST:
            case WOODEN_DOOR:
            case COBBLESTONE_STAIRS:
            case WALL_SIGN:
            case STONE_PLATE:
            case IRON_DOOR_BLOCK:
            case WOOD_PLATE:
            case REDSTONE_ORE:
            case GLOWING_REDSTONE_ORE:
            case ICE:
            case SNOW_BLOCK:
            case CACTUS:
            case CLAY:
            case JUKEBOX:
            case FENCE:
            case PUMPKIN:
            case NETHERRACK:
            case SOUL_SAND:
            case GLOWSTONE:
            case JACK_O_LANTERN:
            case CAKE_BLOCK:
            case STAINED_GLASS:
            case TRAP_DOOR:
            case MONSTER_EGGS:
            case SMOOTH_BRICK:
            case HUGE_MUSHROOM_1:
            case HUGE_MUSHROOM_2:
            case IRON_FENCE:
            case THIN_GLASS:
            case MELON_BLOCK:
            case FENCE_GATE:
            case BRICK_STAIRS:
            case SMOOTH_STAIRS:
            case MYCEL:
            case NETHER_BRICK:
            case NETHER_FENCE:
            case NETHER_BRICK_STAIRS:
            case ENCHANTMENT_TABLE:
            case BREWING_STAND:
            case CAULDRON:
            case ENDER_PORTAL_FRAME:
            case ENDER_STONE:
            case DRAGON_EGG:
            case REDSTONE_LAMP_OFF:
            case REDSTONE_LAMP_ON:
            case WOOD_DOUBLE_STEP:
            case WOOD_STEP:
            case SANDSTONE_STAIRS:
            case EMERALD_ORE:
            case ENDER_CHEST:
            case EMERALD_BLOCK:
            case SPRUCE_WOOD_STAIRS:
            case BIRCH_WOOD_STAIRS:
            case JUNGLE_WOOD_STAIRS:
            case COMMAND:
            case BEACON:
            case COBBLE_WALL:
            case ANVIL:
            case TRAPPED_CHEST:
            case GOLD_PLATE:
            case IRON_PLATE:
            case DAYLIGHT_DETECTOR:
            case REDSTONE_BLOCK:
            case QUARTZ_ORE:
            case HOPPER:
            case QUARTZ_BLOCK:
            case QUARTZ_STAIRS:
            case DROPPER:
            case STAINED_CLAY:
            case HAY_BLOCK:
            case HARD_CLAY:
            case COAL_BLOCK:
            case STAINED_GLASS_PANE:
            case LEAVES_2:
            case LOG_2:
            case ACACIA_STAIRS:
            case DARK_OAK_STAIRS:
            case PACKED_ICE:
            case RED_SANDSTONE:
            case SLIME_BLOCK:
            case BARRIER:
            case IRON_TRAPDOOR:
            case PRISMARINE:
            case SEA_LANTERN:
            case DOUBLE_STONE_SLAB2:
            case RED_SANDSTONE_STAIRS:
            case STONE_SLAB2:
            case SPRUCE_FENCE_GATE:
            case BIRCH_FENCE_GATE:
            case JUNGLE_FENCE_GATE:
            case DARK_OAK_FENCE_GATE:
            case ACACIA_FENCE_GATE:
            case SPRUCE_FENCE:
            case BIRCH_FENCE:
            case JUNGLE_FENCE:
            case DARK_OAK_FENCE:
            case ACACIA_FENCE:
                return true;
            default:
                return false;
        }
    }

    public boolean isTransparent() {
        if (!isBlock()) {
            return false;
        }
        switch (this) {
            case AIR:
            case SAPLING:
            case POWERED_RAIL:
            case DETECTOR_RAIL:
            case LONG_GRASS:
            case DEAD_BUSH:
            case YELLOW_FLOWER:
            case RED_ROSE:
            case BROWN_MUSHROOM:
            case RED_MUSHROOM:
            case TORCH:
            case FIRE:
            case REDSTONE_WIRE:
            case CROPS:
            case LADDER:
            case RAILS:
            case LEVER:
            case REDSTONE_TORCH_OFF:
            case REDSTONE_TORCH_ON:
            case STONE_BUTTON:
            case SNOW:
            case SUGAR_CANE_BLOCK:
            case PORTAL:
            case DIODE_BLOCK_OFF:
            case DIODE_BLOCK_ON:
            case PUMPKIN_STEM:
            case MELON_STEM:
            case VINE:
            case WATER_LILY:
            case NETHER_WARTS:
            case ENDER_PORTAL:
            case COCOA:
            case TRIPWIRE_HOOK:
            case TRIPWIRE:
            case FLOWER_POT:
            case CARROT:
            case POTATO:
            case WOOD_BUTTON:
            case SKULL:
            case REDSTONE_COMPARATOR_OFF:
            case REDSTONE_COMPARATOR_ON:
            case ACTIVATOR_RAIL:
            case CARPET:
            case DOUBLE_PLANT:
            case DAYLIGHT_DETECTOR_INVERTED:
                return true;
            default:
                return false;
        }
    }

    public boolean isFlammable() {
        if (!isBlock()) {
            return false;
        }
        switch (this) {
            case WOOD:
            case LOG:
            case LEAVES:
            case NOTE_BLOCK:
            case BED_BLOCK:
            case LONG_GRASS:
            case DEAD_BUSH:
            case WOOL:
            case TNT:
            case BOOKSHELF:
            case WOOD_STAIRS:
            case CHEST:
            case WORKBENCH:
            case SIGN_POST:
            case WOODEN_DOOR:
            case WALL_SIGN:
            case WOOD_PLATE:
            case JUKEBOX:
            case FENCE:
            case TRAP_DOOR:
            case HUGE_MUSHROOM_1:
            case HUGE_MUSHROOM_2:
            case VINE:
            case FENCE_GATE:
            case WOOD_DOUBLE_STEP:
            case WOOD_STEP:
            case SPRUCE_WOOD_STAIRS:
            case BIRCH_WOOD_STAIRS:
            case JUNGLE_WOOD_STAIRS:
            case TRAPPED_CHEST:
            case DAYLIGHT_DETECTOR:
            case CARPET:
            case LEAVES_2:
            case LOG_2:
            case ACACIA_STAIRS:
            case DARK_OAK_STAIRS:
            case DOUBLE_PLANT:
            case SPRUCE_FENCE_GATE:
            case BIRCH_FENCE_GATE:
            case JUNGLE_FENCE_GATE:
            case DARK_OAK_FENCE_GATE:
            case ACACIA_FENCE_GATE:
            case SPRUCE_FENCE:
            case BIRCH_FENCE:
            case JUNGLE_FENCE:
            case DARK_OAK_FENCE:
            case ACACIA_FENCE:
                return true;
            default:
                return false;
        }
    }

    public boolean isBurnable() {
        if (!isBlock()) {
            return false;
        }
        switch (this) {
            case WOOD:
            case LOG:
            case LEAVES:
            case LONG_GRASS:
            case WOOL:
            case YELLOW_FLOWER:
            case RED_ROSE:
            case TNT:
            case BOOKSHELF:
            case WOOD_STAIRS:
            case FENCE:
            case VINE:
            case WOOD_DOUBLE_STEP:
            case WOOD_STEP:
            case SPRUCE_WOOD_STAIRS:
            case BIRCH_WOOD_STAIRS:
            case JUNGLE_WOOD_STAIRS:
            case HAY_BLOCK:
            case COAL_BLOCK:
            case LEAVES_2:
            case LOG_2:
            case CARPET:
            case DOUBLE_PLANT:
            case DEAD_BUSH:
            case FENCE_GATE:
            case SPRUCE_FENCE_GATE:
            case BIRCH_FENCE_GATE:
            case JUNGLE_FENCE_GATE:
            case DARK_OAK_FENCE_GATE:
            case ACACIA_FENCE_GATE:
            case SPRUCE_FENCE:
            case BIRCH_FENCE:
            case JUNGLE_FENCE:
            case DARK_OAK_FENCE:
            case ACACIA_FENCE:
                return true;
            default:
                return false;
        }
    }

    public boolean isOccluding() {
        if (!isBlock()) {
            return false;
        }
        switch (this) {
            case STONE:
            case GRASS:
            case DIRT:
            case COBBLESTONE:
            case WOOD:
            case BEDROCK:
            case SAND:
            case GRAVEL:
            case GOLD_ORE:
            case IRON_ORE:
            case COAL_ORE:
            case LOG:
            case SPONGE:
            case LAPIS_ORE:
            case LAPIS_BLOCK:
            case DISPENSER:
            case SANDSTONE:
            case NOTE_BLOCK:
            case WOOL:
            case GOLD_BLOCK:
            case IRON_BLOCK:
            case DOUBLE_STEP:
            case BRICK:
            case BOOKSHELF:
            case MOSSY_COBBLESTONE:
            case OBSIDIAN:
            case MOB_SPAWNER:
            case DIAMOND_ORE:
            case DIAMOND_BLOCK:
            case WORKBENCH:
            case FURNACE:
            case BURNING_FURNACE:
            case REDSTONE_ORE:
            case GLOWING_REDSTONE_ORE:
            case SNOW_BLOCK:
            case CLAY:
            case JUKEBOX:
            case PUMPKIN:
            case NETHERRACK:
            case SOUL_SAND:
            case JACK_O_LANTERN:
            case MONSTER_EGGS:
            case SMOOTH_BRICK:
            case HUGE_MUSHROOM_1:
            case HUGE_MUSHROOM_2:
            case MELON_BLOCK:
            case MYCEL:
            case NETHER_BRICK:
            case ENDER_PORTAL_FRAME:
            case ENDER_STONE:
            case REDSTONE_LAMP_OFF:
            case REDSTONE_LAMP_ON:
            case WOOD_DOUBLE_STEP:
            case EMERALD_ORE:
            case EMERALD_BLOCK:
            case COMMAND:
            case QUARTZ_ORE:
            case QUARTZ_BLOCK:
            case DROPPER:
            case STAINED_CLAY:
            case HAY_BLOCK:
            case HARD_CLAY:
            case COAL_BLOCK:
            case LOG_2:
            case PACKED_ICE:
            case SLIME_BLOCK:
            case BARRIER:
            case PRISMARINE:
            case RED_SANDSTONE:
                return true;
            default:
                return false;
        }
    }

    public boolean hasGravity() {
        if (!isBlock()) {
            return false;
        }
        switch (this) {
            case SAND:
            case GRAVEL:
            case ANVIL:
                return true;
            default:
                return false;
        }
    }

    public boolean isContainer() {
        if(!isBlock()) {
            return false;
        }
        switch (this) {
            case CHEST:
            case TRAPPED_CHEST:
            case FURNACE:
            case BURNING_FURNACE:
            case DISPENSER:
            case DROPPER:
                return true;
            default:
                return false;
        }
    }

    static {
        for (Type type : Type.values()) {
            typeNames.put(type.name(), type);
        }
    }

}
