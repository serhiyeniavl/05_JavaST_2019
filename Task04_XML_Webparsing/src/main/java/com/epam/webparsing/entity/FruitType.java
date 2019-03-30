
package com.epam.webparsing.entity;

/**
 * <p>Java class for Fruit-type.
 */
public enum FruitType {

    /**
     * Field describes tag content.
     */
    ФРУКТОВОЕ_ЖЕЛЕ("ФРУКТОВОЕ ЖЕЛЕ"),
    /**
     * Field describes tag content.
     */
    СГУЩЕНКА("СГУЩЕНКА");

    /**
     * Enum constant value.
     */
    private final String value;

    /**
     * Constructor - initializes value.
     *
     * @param v const value.
     */
    FruitType(final String v) {
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
    public static FruitType fromValue(final String v) {
        for (FruitType c : FruitType.values()) {
            if (c.value.equalsIgnoreCase(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    /**
     * @return string representation.
     */
    @Override
    public String toString() {
        return "FruitType{"
                + "value='" + value + '\''
                + '}';
    }
}
