package io.github.cafeteriaguild.coffeecomputers.consts;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Base64;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public class DefaultTextFont {
    public static final int CHAR_COUNT = 256;
    public static final int CHAR_W = 8;
    public static final int CHAR_H = 11;

    public static final int CHAR_LENGTH = CHAR_W * CHAR_H;
    public static final int ALL_CHARS_LENGTH = CHAR_LENGTH * CHAR_COUNT;
    public static final boolean[] ALL_CHARS_MASK = new boolean[ALL_CHARS_LENGTH];

    static {
        // The following code below populates the ALL_CHARS_MASK with the data.
        // God please have mercy on me for what is coded next.

        var compressed = "dVY9axwxEFWxxRYLFmGLLVwIs4UKE5ZwhQoRhFFxGBeHWcJhQtjCmBQurlyCuPxl/4I40nxo1hcyZ7i3c7OjpzejkZ" +
            "USc/E1ztEBTHOKrwCVTSk5DVA7QTol7djLUA3eDwDO55e7u5fzWf3Xrrvdfj/hwjE6TZnF/OjNsgD8evP15uUFYDilU8CA5jpdN0xBa" +
            "+Q2FlMjJJjnXd/3AJdj3HWr5Pb8Wn4rcQYNFMp33eeQBuKlpzQJxRASgvE8kktr58iZnNOX24G8WoG77y17rU35A4/aB9esEBD3rTZj" +
            "BK/Vu+N+V2Db1qSdNsborsCpbZqmnYjOIHTSloPmh/SREwiZExsTsBYP8zM2gZ5ACfQ2g4kVNhjQ9WNMDVQghbUh72DCGhGm2LS0bq6" +
            "xI2/+9sy3/EjEEGqgo41um0oXGRdX/oE4tKSkv3t6+hE8elNuJNBsjZlCXNEbQsCFi4+8KZwClRAhbN6Hh0gkoyRzUDjwNsUoYH/aU0" +
            "AAg2TxdebX4vP8wBlgYeKw8mrgxcIi34gcHC8BHYnqRMlQgLXVO7/ialZbXtjW11JRDJk5UwwyhNw5Lerr2mJYbhsvGhbOhW43E6Lx0" +
            "eOOj8+kJCgcSZ1d3rFnb8KydNrr2ny+1H6tGbDBVeXbFIVxn8aMdrJjPTjM4+c8RyJaNaMRgjDnXVFglel47M/sJdVR4ZUOrOQtuXyF" +
            "VjOcZ/baqlDkXWSNssAJt5mbFg+klrpNWecWe/3LQ9Vx3H/Cv63ab9kIXmUj+CfbxyCOJVhiCb5nkySKkpRAgldoHCurlEeOJVgCCb6" +
            "jXXAlApT+St5+32zm7R8jvhz7cSebII4l+DsbQaL0Jlk4liDx3QiqLvosj/vStAUPo1kNllBFaKMIxU48PmFoI9zcInZ7Ud0/Pt57LL" +
            "fbO3b39mB7PkrUhtv565+evt35i5E80Z0oEzzBiV0C3oRqMfmD0IQNidK/v0qLb269sr1hKwHcHzmBOal/zo3KbO2BL5/b28hwtNC5z" +
            "/Yne7Mghs78gmMXToBAFyu0B4ESoAX6w2E9HL7zrI4OSC7lSsHJ1QksQ4WgFe8EIxovH4HaVmjFm2cGT67ewoiGXbg6XjuBLlZ5yi4Y" +
            "SsBmJmzuzUVJMoHaqprhQ4DlG1IP/cAkjzngGFBfHrqdwMyMYWZWoQRogUrdPqbDZxnVpK8MaIFQt+BrsRBOcnAGgVquGbvxtl40qwN" +
            "6kUbrBP5H301Tlu7n/2FcFnijrxd9vejrRV8JwFk9ib62ev8C";

        var inf = new Inflater(true);
        var bin = Base64.getDecoder().decode(compressed);
        var i = 0;

        try (var stream = new InflaterInputStream(new ByteArrayInputStream(bin), inf, bin.length)) {
            while (i < ALL_CHARS_LENGTH) {
                var b = stream.read();
                ALL_CHARS_MASK[i++] = (b & 128) != 0;
                ALL_CHARS_MASK[i++] = (b & 64) != 0;
                ALL_CHARS_MASK[i++] = (b & 32) != 0;
                ALL_CHARS_MASK[i++] = (b & 16) != 0;
                ALL_CHARS_MASK[i++] = (b & 8) != 0;
                ALL_CHARS_MASK[i++] = (b & 4) != 0;
                ALL_CHARS_MASK[i++] = (b & 2) != 0;
                ALL_CHARS_MASK[i++] = (b & 1) != 0;
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } finally {
            inf.end();
        }
    }
}
