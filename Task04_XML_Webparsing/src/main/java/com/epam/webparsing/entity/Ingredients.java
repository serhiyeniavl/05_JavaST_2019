
package com.epam.webparsing.entity;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Ingredients complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Ingredients">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="water" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="sugar" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="fructose" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="vanillin" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Ingredients", namespace = "http://www.epam.by/candies", propOrder = {
    "water",
    "sugar",
    "fructose",
    "vanillin"
})
public class Ingredients {

    @XmlElement(namespace = "http://www.epam.by/candies", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Integer water;
    @XmlElement(namespace = "http://www.epam.by/candies", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected Integer sugar;
    @XmlElement(namespace = "http://www.epam.by/candies", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected Integer fructose;
    @XmlElement(namespace = "http://www.epam.by/candies", required = true)
    protected String vanillin;

    /**
     * Gets the value of the water property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public Integer getWater() {
        return water;
    }

    /**
     * Sets the value of the water property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setWater(final Integer value) {
        this.water = value;
    }

    /**
     * Gets the value of the sugar property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public Integer getSugar() {
        return sugar;
    }

    /**
     * Sets the value of the sugar property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSugar(final Integer value) {
        this.sugar = value;
    }

    /**
     * Gets the value of the fructose property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public Integer getFructose() {
        return fructose;
    }

    /**
     * Sets the value of the fructose property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFructose(final Integer value) {
        this.fructose = value;
    }

    /**
     * Gets the value of the vanillin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVanillin() {
        return vanillin;
    }

    /**
     * Sets the value of the vanillin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVanillin(final String value) {
        this.vanillin = value;
    }

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
