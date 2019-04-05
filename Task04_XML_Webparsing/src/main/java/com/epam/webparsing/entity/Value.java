
package com.epam.webparsing.entity;

import java.util.Objects;

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
     * Gets the getValue of the protein property.
     *
     * @return possible object is
     * {@link Double }
     */
    public Double getProtein() {
        return protein;
    }

    /**
     * Sets the getValue of the protein property.
     *
     * @param value allowed object is
     *              {@link Double }
     */
    public void setProtein(final Double value) {
        this.protein = value;
    }

    /**
     * Gets the getValue of the carbohydrates property.
     *
     * @return possible object is
     * {@link Double }
     */
    public Double getCarbohydrates() {
        return carbohydrates;
    }

    /**
     * Sets the getValue of the carbohydrates property.
     *
     * @param value allowed object is
     *              {@link Double }
     */
    public void setCarbohydrates(final Double value) {
        this.carbohydrates = value;
    }

    /**
     * Gets the getValue of the fats property.
     *
     * @return possible object is
     * {@link Double }
     */
    public Double getFats() {
        return fats;
    }

    /**
     * Sets the getValue of the fats property.
     *
     * @param value allowed object is
     *              {@link Double }
     */
    public void setFats(final Double value) {
        this.fats = value;
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
        Value value = (Value) object;
        return Objects.equals(protein, value.protein)
                && Objects.equals(carbohydrates, value.carbohydrates)
                && Objects.equals(fats, value.fats);
    }

    /**
     * @return hash code of this obj.
     */
    @Override
    public int hashCode() {
        return Objects.hash(protein, carbohydrates, fats);
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
