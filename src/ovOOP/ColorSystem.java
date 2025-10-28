package ovOOP;

import java.text.DecimalFormat;

public class ColorSystem {
    // Reset
    public static final String RESET = "\u001B[0m";

    // Regular Colors
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    // Bright Colors
    public static final String BRIGHT_BLACK = "\u001B[90m";
    public static final String BRIGHT_RED = "\u001B[91m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_BLUE = "\u001B[94m";
    public static final String BRIGHT_PURPLE = "\u001B[95m";
    public static final String BRIGHT_CYAN = "\u001B[96m";
    public static final String BRIGHT_WHITE = "\u001B[97m";

    // Background Colors
    public static final String BLACK_BG = "\u001B[40m";
    public static final String RED_BG = "\u001B[41m";
    public static final String GREEN_BG = "\u001B[42m";
    public static final String YELLOW_BG = "\u001B[43m";
    public static final String BLUE_BG = "\u001B[44m";
    public static final String PURPLE_BG = "\u001B[45m";
    public static final String CYAN_BG = "\u001B[46m";
    public static final String WHITE_BG = "\u001B[47m";

    // Bright Background Colors
    public static final String BRIGHT_BLACK_BG = "\u001B[100m";
    public static final String BRIGHT_RED_BG = "\u001B[101m";
    public static final String BRIGHT_GREEN_BG = "\u001B[102m";
    public static final String BRIGHT_YELLOW_BG = "\u001B[103m";
    public static final String BRIGHT_BLUE_BG = "\u001B[104m";
    public static final String BRIGHT_PURPLE_BG = "\u001B[105m";
    public static final String BRIGHT_CYAN_BG = "\u001B[106m";
    public static final String BRIGHT_WHITE_BG = "\u001B[107m";

    // Supported Text Styles
    public static final String BOLD = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";
    public static final String REVERSED = "\u001B[7m"; // Swaps foreground/background

    public static String withLargeIntegers(double value) {
        DecimalFormat df = new DecimalFormat("###,###,##0.00");
        return df.format(value);
    }

    public static String[] darkColorPaletteOptions = { ColorSystem.BLACK, ColorSystem.RED,
            ColorSystem.GREEN, ColorSystem.YELLOW,
            ColorSystem.BLUE, ColorSystem.PURPLE, ColorSystem.CYAN, ColorSystem.WHITE };
    public static String[] lightColorPaletteOptions = { ColorSystem.BRIGHT_BLACK, ColorSystem.BRIGHT_RED,
            ColorSystem.BRIGHT_GREEN, ColorSystem.BRIGHT_YELLOW,
            ColorSystem.BRIGHT_BLUE, ColorSystem.BRIGHT_PURPLE, ColorSystem.BRIGHT_CYAN, ColorSystem.BRIGHT_WHITE };

    public static String[] colorPalette = { BLUE, CYAN };
}
