
package com.epam.webparsing.entity;

/**
 * <p>Java class for Chocolate-type.
 */
public enum ChocolateType {

    /**
     * Field describes tag content.
     */
    ШОКОЛОДНАЯ_ГЛАЗУРЬ("ШОКОЛОДНАЯ ГЛАЗУРЬ"),
    /**
     * Field describes tag content.
     */
    КАКАО_ШОКОЛАД("КАКАО-ШОКОЛАД");

    /**
     * Enum constant value.
     */
    private final String value;

    /**
     * Constructor - initializes value.
     * @param v const value.
     */
    ChocolateType(final String v) {
        value = v;
    }

    /**
     * @return value of constant.
     */
    public String value() {
        return value;
    }

    /**
     * @param v string to convert in enum constant.
     * @return enum constant from input string.
     */
    public static ChocolateType fromValue(final String v) {
        for (ChocolateType c : ChocolateType.values()) {
            if (c.value.equalsIgnoreCase(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
