package io.github.cafeteriaguild.coffeecomputers.utils;

import io.github.cafeteriaguild.coffeecomputers.consts.DefaultTextFont;

import java.util.Arrays;

public class DefaultTextFrameBuffer {
    /**
     * The screen's width and height, in pixels.
     */
    private final int screenWidth, screenHeight;

    /**
     * The screen's width and height, in characters.
     */
    private final int textWidth, textHeight;

    /**
     * Text rendering offset, in pixels.
     */
    private final int offsetX, offsetY;

    /**
     * Current position of the text cursor on the text array.
     */
    private int positionX = 0, positionY = 0;

    /**
     * Current foreground and background colors.
     */
    private ColorPalette currentFg = ColorPalette.WHITE, currentBg = ColorPalette.BLACK;

    /**
     * The text array, with length = textWidth * textHeight.
     */
    private final char[] chArray;

    /**
     * The foreground and background array, with length = textWidth * textHeight.
     */
    private final ColorPalette[] fgArray, bgArray;

    public DefaultTextFrameBuffer(int w, int h) {
        this.screenWidth = w;
        this.screenHeight = h;

        this.textWidth = w / 6;
        this.textHeight = h / 9;

        this.offsetX = w % 6 / 2;
        this.offsetY = h % 9 / 2;

        this.chArray = new char[w * h];
        Arrays.fill(this.chArray, ' ');
        this.fgArray = new ColorPalette[w * h];
        Arrays.fill(this.fgArray, this.currentFg);
        this.bgArray = new ColorPalette[w * h];
        Arrays.fill(this.bgArray, this.currentBg);
    }

    public void setCursor(int x, int y) {
        positionX = x;
        positionY = y;
    }

    public void setFg(ColorPalette color) {
        currentFg = color;
    }

    public void setBg(ColorPalette color) {
        currentBg = color;
    }

    /**
     * Prints a string into the text buffer.
     *
     * @param s the string
     * @return true if a frame rendering is suggested.
     */
    public boolean print(String s) {
        boolean suggestRender = false;

        for (char c : s.toCharArray()) {
            if (c == '\n') {
                suggestRender = true;
                positionX = 0;
                if (positionY + 1 < textHeight) {
                    positionY++;
                } else {
                    // discard first row
                    System.arraycopy(chArray, textWidth, chArray, 0, chArray.length - textWidth);
                    System.arraycopy(fgArray, textWidth, fgArray, 0, fgArray.length - textWidth);
                    System.arraycopy(bgArray, textWidth, bgArray, 0, bgArray.length - textWidth);
                }
            } else if (positionX < textWidth) {
                // set character data
                int pos = positionX++ + positionY * textWidth;
                chArray[pos] = c;
                fgArray[pos] = currentFg;
                bgArray[pos] = currentBg;
            } else {
                suggestRender = true;
                positionX = 0;
                if (positionY + 1 < textHeight) {
                    // set character data
                    int pos = positionY++ * textWidth;
                    chArray[pos] = c;
                    fgArray[pos] = currentFg;
                    bgArray[pos] = currentBg;
                } else {

                    // discard first row
                    System.arraycopy(chArray, textWidth, chArray, 0, chArray.length - textWidth);
                    System.arraycopy(fgArray, textWidth, fgArray, 0, fgArray.length - textWidth);
                    System.arraycopy(bgArray, textWidth, bgArray, 0, bgArray.length - textWidth);

                    // set character data
                    int pos = positionY * textWidth;
                    chArray[pos] = c;
                    fgArray[pos] = currentFg;
                    bgArray[pos] = currentBg;
                }
            }
        }

        return suggestRender;
    }

    /**
     * Renders the text buffer into a frame.
     *
     * @return byte array with the rendered frame.
     */
    public byte[] render() {
        var mask = DefaultTextFont.ALL_CHARS_MASK;
        var output = new byte[screenHeight * screenWidth];

        for (int y = 0; y < textHeight; y++) {
            for (int x = 0; x < textWidth; x++) {
                var ch = chArray[x + y * screenWidth];
                var fg = fgArray[x + y * screenWidth];
                var bg = bgArray[x + y * screenWidth];

                var maskOffset = ch * DefaultTextFont.ALL_CHARS_LENGTH;
                var startX = x * 6 + offsetX;
                var startY = y * 9 + offsetY;

                for (int fontY = 0; fontY < DefaultTextFont.CHAR_H; fontY++) {
                    var pixelY = (startY + fontY) * screenWidth;
                    var maskY = maskOffset + fontY * DefaultTextFont.CHAR_W;

                    for (int fontX = 0; fontX < DefaultTextFont.CHAR_W; fontX++) {
                        output[pixelY + startX + fontX] = mask[maskY + fontX] ? fg.value : bg.value;
                    }
                }
            }
        }

        return output;
    }
}
