
package com.epam.webparsing.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Chocolate-type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Chocolate-type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Шоколодная глазурь"/>
 *     &lt;enumeration value="Какао-шоколад"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Chocolate-type", namespace = "http://www.epam.by/candies")
@XmlEnum
public enum ChocolateType {

    @XmlEnumValue("\u0428\u043e\u043a\u043e\u043b\u043e\u0434\u043d\u0430\u044f \u0433\u043b\u0430\u0437\u0443\u0440\u044c")
    ШОКОЛОДНАЯ_ГЛАЗУРЬ("\u0428\u043e\u043a\u043e\u043b\u043e\u0434\u043d\u0430\u044f \u0433\u043b\u0430\u0437\u0443\u0440\u044c"),
    @XmlEnumValue("\u041a\u0430\u043a\u0430\u043e-\u0448\u043e\u043a\u043e\u043b\u0430\u0434")
    КАКАО_ШОКОЛАД("\u041a\u0430\u043a\u0430\u043e-\u0448\u043e\u043a\u043e\u043b\u0430\u0434");
    private final String value;

    ChocolateType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ChocolateType fromValue(String v) {
        for (ChocolateType c: ChocolateType.values()) {
            if (c.value.equalsIgnoreCase(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
