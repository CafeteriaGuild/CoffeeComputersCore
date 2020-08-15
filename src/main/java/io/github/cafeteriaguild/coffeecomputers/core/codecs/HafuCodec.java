package io.github.cafeteriaguild.coffeecomputers.core.codecs;

import org.jetbrains.annotations.NotNull;

/**
 * "Hafu" codec is a simple codec that uses the assumption `bytes of "src" are between 0 and 15`
 * to compress the byte array losslessly in half.
 */
public class HafuCodec implements Codec {
    public static final Codec INSTANCE = new HafuCodec();

    private HafuCodec() {
    }

    @NotNull
    @Override
    public byte[] codec(@NotNull byte[] src) {
        byte[] bin = new byte[src.length / 2];
        int i = 0;
        for (int j = 0; j < bin.length; j++) {
            bin[j] = ((byte) ((src[i++] << 4) | (src[i++] & 15)));
        }
        return bin;
    }

    @NotNull
    @Override
    public byte[] uncodec(@NotNull byte[] bin) {
        byte[] src = new byte[bin.length * 2];
        int i = 0;
        for (byte b : bin) {
            int v = ((int) b) & 0xff;
            src[i++] = (byte) (v >> 4);
            src[i++] = (byte) (v & 0b1111);
        }
        return src;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
