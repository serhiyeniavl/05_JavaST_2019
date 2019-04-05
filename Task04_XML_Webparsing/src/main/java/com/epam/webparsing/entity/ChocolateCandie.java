
package com.epam.webparsing.entity;

import java.util.Objects;

/**
 * <p>Java class for Chocolate-candie complex type.
 */
public class ChocolateCandie extends Candie {

    /**
     * Chocolate type this candie has.
     */
    private ChocolateType chocolateType;

    /**
     * Gets the getValue of the chocolateType property.
     *
     * @return possible object is
     * {@link ChocolateType }
     */
    @Override
    public ChocolateType getChocolateType() {
        return chocolateType;
    }

    /**
     * Sets the getValue of the chocolateType property.
     *
     * @param value allowed object is
     *              {@link ChocolateType }
     */
    @Override
    public void setChocolateType(final ChocolateType value) {
        this.chocolateType = value;
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
        ChocolateCandie that = (ChocolateCandie) object;
        return chocolateType == that.chocolateType;
    }

    /**
     * @return hash code of this obj.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), chocolateType);
    }

    /**
     * @return string representation.
     */
    @Override
    public String toString() {
        return "ChocolateCandie{"
                + "chocolateType=" + chocolateType
                + super.toString();
    }
}
