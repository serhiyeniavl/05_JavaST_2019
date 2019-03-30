
package com.epam.webparsing.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Chocolate-candie complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Chocolate-candie">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.epam.by/candies}Candie">
 *       &lt;sequence>
 *         &lt;element name="chocolate-type" type="{http://www.epam.by/candies}Chocolate-type"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Chocolate-candie", namespace = "http://www.epam.by/candies", propOrder = {
        "chocolateType"
})
public class ChocolateCandie extends Candie {

    @XmlElement(name = "chocolate-type", namespace = "http://www.epam.by/candies", required = true)
    @XmlSchemaType(name = "string")
    protected ChocolateType chocolateType;

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
    public void setChocolateType(ChocolateType value) {
        this.chocolateType = value;
    }

    @Override
    public String toString() {
        return "ChocolateCandie{"
                + "chocolateType=" + chocolateType
                + super.toString();
    }
}
