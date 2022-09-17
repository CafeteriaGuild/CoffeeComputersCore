package io.github.cafeteriaguild.coffeecomputers.codecs;

import org.jetbrains.annotations.NotNull;

/**
 * A no-implementation codec.
 */
public class NoCodec implements Codec {
    private static final Codec INSTANCE = new NoCodec();

    private NoCodec() {
    }

    @NotNull
    @Override
    public byte[] codec(@NotNull byte[] src) {
        return src;
    }

    @NotNull
    @Override
    public byte[] uncodec(@NotNull byte[] bin) {
        return bin;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
