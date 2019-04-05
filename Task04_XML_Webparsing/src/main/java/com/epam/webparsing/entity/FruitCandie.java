
package com.epam.webparsing.entity;

import java.util.Objects;

/**
 * <p>Java class for Fruit-candie complex type.
 */
public class FruitCandie extends Candie {

    /**
     * Fruit type this candie has.
     */
    private FruitType fruitType;

    /**
     * Gets the getValue of the fruitType property.
     *
     * @return possible object is
     * {@link FruitType }
     */
    @Override
    public FruitType getFruitType() {
        return fruitType;
    }

    /**
     * Sets the getValue of the fruitType property.
     *
     * @param value allowed object is
     *              {@link FruitType }
     */
    @Override
    public void setFruitType(final FruitType value) {
        this.fruitType = value;
    }

    /**
     * Comparing objects on coincidence.
     * @param object object to compare.
     * @return true if objects are equal.
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        FruitCandie candie = (FruitCandie) object;
        return fruitType == candie.fruitType;
    }

    /**
     * @return hash code of this obj.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fruitType);
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
