package io.github.cafeteriaguild.coffeecomputers.codecs;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/**
 * "Deflater" codec provides ZLIB deflation/inflation on top of another codec.
 */
public class DeflaterCodec implements Codec {
    private final Codec underlying;
    private final int level;

    public DeflaterCodec(Codec underlying, int level) {
        this.underlying = underlying;
        this.level = level;
    }

    @NotNull
    @Override
    public byte[] codec(@NotNull byte[] src) throws IOException {
        var def = configureDeflater();
        try (var out = new ByteArrayOutputStream(src.length); var stream = new DeflaterOutputStream(out, def, src.length, false)) {
            stream.write(underlying.codec(src));
            stream.finish();
            return out.toByteArray();
        } finally {
            def.end();
        }
    }

    @NotNull
    @Override
    public byte[] uncodec(@NotNull byte[] bin) throws IOException {
        var inf = configureInflater();
        try (var stream = new InflaterInputStream(new ByteArrayInputStream(bin), inf, bin.length)) {
            return underlying.uncodec(stream.readAllBytes());
        } finally {
            inf.end();
        }
    }

    protected Deflater configureDeflater() {
        var def = new Deflater(level, true);
        def.setStrategy(Deflater.FILTERED);
        return def;
    }

    protected Inflater configureInflater() {
        return new Inflater(true);
    }

    @Override
    public String toString() {
        return underlying + " |> " + getClass().getSimpleName() + "(" + level + ")";
    }
}
