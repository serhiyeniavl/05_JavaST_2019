package com.epam.webparsing.xml_tag;

/**
 * Enum describes all tags xml file has.
 */
public enum CandieEnum {
    /**
     * Root tag.
     */
    CANDIES("candies"),
    /**
     * Root candie tag.
     */
    CHOCOLATE_CANDIE("chocolate-candie"),
    /**
     * Tag ingredients.
     */
    INGREDIENTS("ingredients"),
    /**
     * Tag value.
     */
    VALUE("value"),
    /**
     * Root candie tag.
     */
    FRUIT_CANDIE("fruit-candie"),
    /**
     * Tag energy.
     */
    ENERGY("energy"),
    /**
     * Tag water.
     */
    WATER("water"),
    /**
     * Tag sugar.
     */
    SUGAR("sugar"),
    /**
     * Tag fructose.
     */
    FRUCTOSE("fructose"),
    /**
     * Tag vanillin.
     */
    VANILLIN("vanillin"),
    /**
     * Tag protein.
     */
    PROTEIN("protein"),
    /**
     * Tag carbohydrates.
     */
    CARBOHYDRATES("carbohydrates"),
    /**
     * Tag fats.
     */
    FATS("fats"),
    /**
     * Tag date.
     */
    DATE("date"),
    /**
     * Tag chocolate-type.
     */
    CHOCOLATE_TYPE("chocolate-type"),
    /**
     * Tag fruit-type.
     */
    FRUIT_TYPE("fruit-type");

    /**
     * Enum constant value.
     */
    private String value;

    /**
     * Constructor - initializes value.
     *
     * @param val const value.
     */
    CandieEnum(final String val) {
        this.value = val;
    }

    /**
     * @return value of constant.
     */
    public String getValue() {
        return value;
    }
}
