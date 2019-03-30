
package com.epam.webparsing.entity;

/**
 * <p>Java class for Value complex type.
 */
public class Value {

    /**
     * Data from tag protein.
     */
    private Double protein;
    /**
     * Data from tag carbohydrates.
     */
    private Double carbohydrates;
    /**
     * Data from tag fats.
     */
    private Double fats;

    /**
     * Gets the value of the protein property.
     *
     * @return possible object is
     * {@link Double }
     */
    public Double getProtein() {
        return protein;
    }

    /**
     * Sets the value of the protein property.
     *
     * @param value allowed object is
     *              {@link Double }
     */
    public void setProtein(final Double value) {
        this.protein = value;
    }

    /**
     * Gets the value of the carbohydrates property.
     *
     * @return possible object is
     * {@link Double }
     */
    public Double getCarbohydrates() {
        return carbohydrates;
    }

    /**
     * Sets the value of the carbohydrates property.
     *
     * @param value allowed object is
     *              {@link Double }
     */
    public void setCarbohydrates(final Double value) {
        this.carbohydrates = value;
    }

    /**
     * Gets the value of the fats property.
     *
     * @return possible object is
     * {@link Double }
     */
    public Double getFats() {
        return fats;
    }

    /**
     * Sets the value of the fats property.
     *
     * @param value allowed object is
     *              {@link Double }
     */
    public void setFats(final Double value) {
        this.fats = value;
    }

    /**
     * @return string representation.
     */
    @Override
    public String toString() {
        return "Value{"
                + "protein=" + protein
                + ", carbohydrates=" + carbohydrates
                + ", fats=" + fats
                + '}';
    }
}
