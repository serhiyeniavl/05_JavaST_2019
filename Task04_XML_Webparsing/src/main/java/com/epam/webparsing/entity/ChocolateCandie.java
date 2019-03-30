
package com.epam.webparsing.entity;

/**
 * <p>Java class for Chocolate-candie complex type.
 */
public class ChocolateCandie extends Candie {

    /**
     * Chocolate type this candie has.
     */
    private ChocolateType chocolateType;

    /**
     * Gets the value of the chocolateType property.
     *
     * @return possible object is
     * {@link ChocolateType }
     */
    public ChocolateType getChocolateType() {
        return chocolateType;
    }

    /**
     * Sets the value of the chocolateType property.
     *
     * @param value allowed object is
     *              {@link ChocolateType }
     */
    public void setChocolateType(final ChocolateType value) {
        this.chocolateType = value;
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
