
package com.epam.webparsing.entity;

/**
 * <p>Java class for Candie complex type.
 */
public abstract class Candie {

    /**
     * Candie energy.
     */
    private Integer energy;
    /**
     * Candie ingredients.
     * @see Ingredients
     */
    private Ingredients ingredients = new Ingredients();
    /**
     * Candie value.
     * @see Value
     */
    private Value value = new Value();
    /**
     * Candie date.
     */
    private String date;
    /**
     * Candie production.
     */
    private String production;
    /**
     * Candie name.
     */
    private String name;
    /**
     * Candie filling.
     */
    private String filling;

    /**
     * Gets the value of the energy property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getEnergy() {
        return energy;
    }

    /**
     * Sets the value of the energy property.
     *
     * @param val allowed object is
     *              {@link Integer }
     */
    public void setEnergy(final Integer val) {
        this.energy = val;
    }

    /**
     * Gets the value of the ingredients property.
     *
     * @return possible object is
     * {@link Ingredients }
     */
    public Ingredients getIngredients() {
        return ingredients;
    }

    /**
     * Sets the value of the ingredients property.
     *
     * @param val allowed object is
     *              {@link Ingredients }
     */
    public void setIngredients(final Ingredients val) {
        this.ingredients = val;
    }

    /**
     * Gets the value of the value property.
     *
     * @return possible object is
     * {@link Value }
     */
    public Value getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     *
     * @param val allowed object is
     *            {@link Value }
     */
    public void setValue(final Value val) {
        this.value = val;
    }

    /**
     * Gets the value of the date property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     *
     * @param val allowed object is
     *              {@link String }
     */
    public void setDate(final String val) {
        this.date = val;
    }

    /**
     * Gets the value of the production property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getProduction() {
        return production;
    }

    /**
     * Sets the value of the production property.
     *
     * @param val allowed object is
     *              {@link String }
     */
    public void setProduction(final String val) {
        this.production = val;
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param val allowed object is
     *              {@link String }
     */
    public void setName(final String val) {
        this.name = val;
    }

    /**
     * Gets the value of the filling property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFilling() {
        if (filling == null) {
            return "none";
        } else {
            return filling;
        }
    }

    /**
     * Sets the value of the filling property.
     *
     * @param val allowed object is
     *              {@link String }
     */
    public void setFilling(final String val) {
        this.filling = val;
    }

    /**
     * Sets candie chocolate type.
     * @param chocolateType type to set.
     */
    public void setChocolateType(final ChocolateType chocolateType) {
    }

    /**
     * Sets candie fruit type.
     * @param fruitType type to set.
     */
    public void setFruitType(final FruitType fruitType) {
    }

    /**
     * @return candie type.
     */
    public ChocolateType getChocolateType() {
        return null;
    }

    /**
     * @return candie type.
     */
    public FruitType getFruitType() {
        return null;
    }

    /**
     * @return string representation.
     */
    @Override
    public String toString() {
        return "Candie{"
                + "energy=" + energy
                + ", ingredients=" + ingredients
                + ", value=" + value
                + ", date='" + date + '\''
                + ", production='" + production + '\''
                + ", name='" + name + '\''
                + ", filling='" + filling + '\''
                + '}';
    }
}
