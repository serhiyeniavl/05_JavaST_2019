package by.training.info_system.util;

import java.util.Base64;

public final class Encoder {
    private Encoder() {
    }

    public static String encodeString(final String string) {
        return new String(Base64.getEncoder().withoutPadding().encode(string.getBytes()));
    }

    public static String decodeString(final String string) {
        return new String(Base64.getDecoder().decode(string));
    }
}
