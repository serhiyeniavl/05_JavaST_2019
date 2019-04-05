
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
     * Enum constant getValue.
     */
    private final String value;

    /**
     * Constructor - initializes getValue.
     * @param v const getValue.
     */
    ChocolateType(final String v) {
        value = v;
    }

    /**
     * @return getValue of constant.
     */
    public String getValue() {
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
