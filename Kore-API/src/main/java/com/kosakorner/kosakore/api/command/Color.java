package com.kosakorner.kosakore.api.command;

public enum Color {

    /**
     * Represents black
     */
    BLACK('0'),
    /**
     * Represents dark blue
     */
    DARK_BLUE('1'),
    /**
     * Represents dark green
     */
    DARK_GREEN('2'),
    /**
     * Represents dark blue (aqua)
     */
    DARK_AQUA('3'),
    /**
     * Represents dark red
     */
    DARK_RED('4'),
    /**
     * Represents dark purple
     */
    DARK_PURPLE('5'),
    /**
     * Represents gold
     */
    GOLD('6'),
    /**
     * Represents gray
     */
    GRAY('7'),
    /**
     * Represents dark gray
     */
    DARK_GRAY('8'),
    /**
     * Represents blue
     */
    BLUE('9'),
    /**
     * Represents green
     */
    GREEN('a'),
    /**
     * Represents aqua
     */
    AQUA('b'),
    /**
     * Represents red
     */
    RED('c'),
    /**
     * Represents light purple
     */
    LIGHT_PURPLE('d'),
    /**
     * Represents yellow
     */
    YELLOW('e'),
    /**
     * Represents white
     */
    WHITE('f'),
    /**
     * Represents magical characters that change around randomly
     */
    MAGIC('k'),
    /**
     * Makes the text bold.
     */
    BOLD('l'),
    /**
     * Makes a line appear through the text.
     */
    STRIKETHROUGH('m'),
    /**
     * Makes the text appear underlined.
     */
    UNDERLINE('n'),
    /**
     * Makes the text italic.
     */
    ITALIC('o'),
    /**
     * Resets all previous chat colors or formats.
     */
    RESET('r');

    public static final char COLOR_CHAR = '\u00A7';

    private char code;

    Color(char code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return new String(new char[]{COLOR_CHAR, code});
    }

    /**
     * Translates a string using an alternate color code character into a
     * string that uses the internal Color.COLOR_CODE color code
     * character. The alternate color code character will only be replaced if
     * it is immediately followed by 0-9, A-F, a-f, K-O, k-o, R or r.
     *
     * @param altColorChar    The alternate color code character to replace. Ex: {@literal &}
     * @param textToTranslate Text containing the alternate color code character
     * @return Text containing the ChatColor.COLOR_CODE color code character
     */
    public static String translateAlternateColorCodes(char altColorChar, String textToTranslate) {
        char[] b = textToTranslate.toCharArray();
        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == altColorChar && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1) {
                b[i] = Color.COLOR_CHAR;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }
        return new String(b);
    }

}
