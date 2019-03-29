package com.epam.webparsing.xml_tag;

public enum CandieEnum {
    CANDIES("candies"),
    CHOCOLATE_CANDIE("chocolate-candie"),
    INGREDIENTS("ingredients"),
    VALUE("value"),
    FRUIT_CANDIE("fruit-candie"),
    ENERGY("energy"),
    WATER("water"),
    SUGAR("sugar"),
    FRUCTOSE("fructose"),
    VANILLIN("vanillin"),
    PROTEIN("protein"),
    CARBOHYDRATES("carbohydrates"),
    FATS("fats"),
    DATE("date"),
    CHOCOLATE_TYPE("chocolate-type"),
    FRUIT_TYPE("fruit-type");


    private String value;

    CandieEnum(final String val) {
        this.value = val;
    }

    public String getValue() {
        return value;
    }
}
