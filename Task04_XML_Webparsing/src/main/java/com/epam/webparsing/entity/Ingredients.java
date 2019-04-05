
package com.epam.webparsing.entity;

import java.util.Objects;

/**
 * <p>Java class for Ingredients complex type.
 */
public class Ingredients {

    /**
     * Data form tag water.
     */
    private Integer water;
    /**
     * Data form tag sugar.
     */
    private Integer sugar;
    /**
     * Data form tag fructose.
     */
    private Integer fructose;
    /**
     * Data form tag vanillin.
     */
    private String vanillin;

    /**
     * Gets the getValue of the water property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getWater() {
        return water;
    }

    /**
     * Sets the getValue of the water property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setWater(final Integer value) {
        this.water = value;
    }

    /**
     * Gets the getValue of the sugar property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getSugar() {
        return sugar;
    }

    /**
     * Sets the getValue of the sugar property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setSugar(final Integer value) {
        this.sugar = value;
    }

    /**
     * Gets the getValue of the fructose property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getFructose() {
        return fructose;
    }

    /**
     * Sets the getValue of the fructose property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setFructose(final Integer value) {
        this.fructose = value;
    }

    /**
     * Gets the getValue of the vanillin property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getVanillin() {
        return vanillin;
    }

    /**
     * Sets the getValue of the vanillin property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setVanillin(final String value) {
        this.vanillin = value;
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
        Ingredients that = (Ingredients) object;
        return Objects.equals(water, that.water)
                && Objects.equals(sugar, that.sugar)
                && Objects.equals(fructose, that.fructose)
                && Objects.equals(vanillin, that.vanillin);
    }

    /**
     * @return hash code of this obj.
     */
    @Override
    public int hashCode() {
        return Objects.hash(water, sugar, fructose, vanillin);
    }

    /**
     * @return string representation.
     */
    @Override
    public String toString() {
        return "Ingredients{"
                + "water=" + water
                + ", sugar=" + sugar
                + ", fructose=" + fructose
                + ", vanillin='" + vanillin + '\''
                + '}';
    }
}
