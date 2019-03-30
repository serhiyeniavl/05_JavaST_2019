
package com.epam.webparsing.entity;

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
     * Gets the value of the water property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getWater() {
        return water;
    }

    /**
     * Sets the value of the water property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setWater(final Integer value) {
        this.water = value;
    }

    /**
     * Gets the value of the sugar property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getSugar() {
        return sugar;
    }

    /**
     * Sets the value of the sugar property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setSugar(final Integer value) {
        this.sugar = value;
    }

    /**
     * Gets the value of the fructose property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getFructose() {
        return fructose;
    }

    /**
     * Sets the value of the fructose property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setFructose(final Integer value) {
        this.fructose = value;
    }

    /**
     * Gets the value of the vanillin property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getVanillin() {
        return vanillin;
    }

    /**
     * Sets the value of the vanillin property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setVanillin(final String value) {
        this.vanillin = value;
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
