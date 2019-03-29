
package com.epam.webparsing.entity;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;


/**
 * <p>Java class for Candie complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Candie">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="energy" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="ingredients" type="{http://www.epam.by/candies}Ingredients"/>
 *         &lt;element name="value" type="{http://www.epam.by/candies}Value"/>
 *         &lt;element name="date" type="{http://www.epam.by/candies}Date"/>
 *       &lt;/sequence>
 *       &lt;attribute name="production" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="filling" type="{http://www.w3.org/2001/XMLSchema}string" default="none" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Candie", namespace = "http://www.epam.by/candies", propOrder = {
        "energy",
        "ingredients",
        "value",
        "date"
})
@XmlSeeAlso({
        ChocolateCandie.class,
        FruitCandie.class
})
public class Candie {

    @XmlElement(namespace = "http://www.epam.by/candies", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected Integer energy;
    @XmlElement(namespace = "http://www.epam.by/candies", required = true)
    protected Ingredients ingredients;
    @XmlElement(namespace = "http://www.epam.by/candies", required = true)
    protected Value value;
    @XmlElement(namespace = "http://www.epam.by/candies", required = true)
    protected String date;
    @XmlAttribute(name = "production", required = true)
    protected String production;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "filling")
    protected String filling;

    /**
     * Gets the value of the energy property.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public Integer getEnergy() {
        return energy;
    }

    /**
     * Sets the value of the energy property.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setEnergy(Integer value) {
        this.energy = value;
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
     * @param value allowed object is
     *              {@link Ingredients }
     */
    public void setIngredients(Ingredients value) {
        this.ingredients = value;
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
     * @param value allowed object is
     *              {@link Value }
     */
    public void setValue(Value value) {
        this.value = value;
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
     * @param value allowed object is
     *              {@link String }
     */
    public void setDate(String value) {
        this.date = value;
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
     * @param value allowed object is
     *              {@link String }
     */
    public void setProduction(String value) {
        this.production = value;
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
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
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
     * @param value allowed object is
     *              {@link String }
     */
    public void setFilling(final String value) {
        this.filling = value;
    }

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
