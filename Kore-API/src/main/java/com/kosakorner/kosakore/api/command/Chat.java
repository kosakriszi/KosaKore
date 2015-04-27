package com.kosakorner.kosakore.api.command;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public enum Chat {

    BLACK('0', 0),
    DARK_BLUE('1', 1),
    DARK_GREEN('2', 2),
    DARK_AQUA('3', 3),
    DARK_RED('4', 4),
    DARK_PURPLE('5', 5),
    GOLD('6', 6),
    GRAY('7', 7),
    DARK_GRAY('8', 8),
    BLUE('9', 9),
    GREEN('a', 10),
    AQUA('b', 11),
    RED('c', 12),
    LIGHT_PURPLE('d', 13),
    YELLOW('e', 14),
    WHITE('f', 15),
    MAGIC('k', 16, true),
    BOLD('l', 17, true),
    STRIKETHROUGH('m', 18, true),
    UNDERLINE('n', 19, true),
    ITALIC('o', 20, true),
    RESET('r', 21);

    public static final char COLOR_CHAR = '§';
    private static final Pattern              STRIP_COLOR_PATTERN;
    private final        int                  intCode;
    private final        char                 code;
    private final        boolean              isFormat;
    private final        String               toString;
    private static final Map<Integer, Chat>   BY_ID;
    private static final Map<Character, Chat> BY_CHAR;

    private Chat(char code, int intCode) {
        this(code, intCode, false);
    }

    private Chat(char code, int intCode, boolean isFormat) {
        this.code = code;
        this.intCode = intCode;
        this.isFormat = isFormat;
        this.toString = new String(new char[]{'§', code});
    }

    public char getChar() {
        return this.code;
    }

    public String toString() {
        return this.toString;
    }

    public boolean isFormat() {
        return this.isFormat;
    }

    public boolean isColor() {
        return !this.isFormat && this != RESET;
    }

    public static Chat getByChar(char code) {
        return (Chat) BY_CHAR.get(Character.valueOf(code));
    }

    public static Chat getByChar(String code) {
        return (Chat) BY_CHAR.get(Character.valueOf(code.charAt(0)));
    }

    public static String stripColor(String input) {
        return input == null ? null : STRIP_COLOR_PATTERN.matcher(input).replaceAll("");
    }

    public static String translateAlternateColorCodes(char altColorChar, String textToTranslate) {
        char[] b = textToTranslate.toCharArray();

        for (int i = 0; i < b.length - 1; ++i) {
            if (b[i] == altColorChar && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1) {
                b[i] = 167;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }

        return new String(b);
    }

    public static String getLastColors(String input) {
        String result = "";
        int length = input.length();

        for (int index = length - 1; index > -1; --index) {
            char section = input.charAt(index);
            if (section == 167 && index < length - 1) {
                char c = input.charAt(index + 1);
                Chat color = getByChar(c);
                if (color != null) {
                    result = color.toString() + result;
                    if (color.isColor() || color.equals(RESET)) {
                        break;
                    }
                }
            }
        }

        return result;
    }

    static {
        STRIP_COLOR_PATTERN = Pattern.compile("(?i)" + String.valueOf('§') + "[0-9A-FK-OR]");
        BY_ID = new HashMap<Integer, Chat>();
        BY_CHAR = new HashMap<Character, Chat>();
        Chat[] arr$ = values();
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            Chat color = arr$[i$];
            BY_ID.put(Integer.valueOf(color.intCode), color);
            BY_CHAR.put(Character.valueOf(color.code), color);
        }

    }

}
