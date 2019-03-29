
package com.epam.webparsing.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Value complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Value">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="protein" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="carbohydrates" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="fats" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Value", namespace = "http://www.epam.by/candies", propOrder = {
        "protein",
        "carbohydrates",
        "fats"
})
public class Value {

    @XmlElement(namespace = "http://www.epam.by/candies", required = true)
    protected Double protein;
    @XmlElement(namespace = "http://www.epam.by/candies", required = true)
    protected Double carbohydrates;
    @XmlElement(namespace = "http://www.epam.by/candies", required = true)
    protected Double fats;

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

    @Override
    public String toString() {
        return "Value{"
                + "protein=" + protein
                + ", carbohydrates=" + carbohydrates
                + ", fats=" + fats
                + '}';
    }
}
