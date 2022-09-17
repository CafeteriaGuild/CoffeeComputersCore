package io.github.cafeteriaguild.coffeecomputers.utils;

public enum ColorPalette {
    WHITE(0xF0F0F0),
    ORANGE(0xF2B233),
    MAGENTA(0xE57FD8),
    LIGHT_BLUE(0x99B2F2),
    YELLOW(0xDEDE6C),
    LIME(0x7FCC19),
    PINK(0xF2B2CC),
    GRAY(0x4C4C4C),
    LIGHT_GRAY(0x999999),
    CYAN(0x4C99B2),
    PURPLE(0xB266E5),
    BLUE(0x3366CC),
    GREEN(0x7F664C),
    BROWN(0x57A64E),
    RED(0xCC4C4C),
    BLACK(0x111111);

    public final int rgb;
    public final byte value;

    ColorPalette(int rgb) {
        this.rgb = rgb;
        this.value = (byte) this.ordinal();
    }
}
