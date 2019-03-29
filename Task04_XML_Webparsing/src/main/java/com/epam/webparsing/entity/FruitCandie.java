
package com.epam.webparsing.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Fruit-candie complex type.
 *
 * <p>The following schema fragment specifies the expected content contained
 * within this class.
 *
 * <pre>
 * &lt;complexType name="Fruit-candie">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.epam.by/candies}Candie">
 *       &lt;sequence>
 *         &lt;element name="fruit-type" type="{http://www.epam.by/candies}
 *         Fruit-type"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Fruit-candie", namespace = "http://www.epam.by/candies",
        propOrder = {
                "fruitType"
        })
public class FruitCandie extends Candie {

    @XmlElement(name = "fruit-type", namespace = "http://www.epam.by/candies",
            required = true)
    @XmlSchemaType(name = "string")
    private FruitType fruitType;

    /**
     * Gets the value of the fruitType property.
     *
     * @return possible object is
     * {@link FruitType }
     */
    public FruitType getFruitType() {
        return fruitType;
    }

    /**
     * Sets the value of the fruitType property.
     *
     * @param value allowed object is
     *              {@link FruitType }
     */
    public void setFruitType(final FruitType value) {
        this.fruitType = value;
    }

    /**
     * @return string object representation.
     */
    @Override
    public String toString() {
        return  "FruitCandie{"
                + "fruitType=" + fruitType
                + super.toString();
    }
}
