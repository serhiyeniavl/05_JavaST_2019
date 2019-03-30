
package com.epam.webparsing.entity;

/**
 * <p>Java class for Fruit-candie complex type.
 */
public class FruitCandie extends Candie {

    /**
     * Fruit type this candie has.
     */
    private FruitType fruitType;

    /**
     * Gets the value of the fruitType property.
     *
     * @return possible object is
     * {@link FruitType }
     */
    public FruitType getFruitType() {
        return fruitType;
    }

    /**
     * Sets the value of the fruitType property.
     *
     * @param value allowed object is
     *              {@link FruitType }
     */
    public void setFruitType(final FruitType value) {
        this.fruitType = value;
    }

    /**
     * @return string object representation.
     */
    @Override
    public String toString() {
        return  "FruitCandie{"
                + "fruitType=" + fruitType
                + super.toString();
    }
}
