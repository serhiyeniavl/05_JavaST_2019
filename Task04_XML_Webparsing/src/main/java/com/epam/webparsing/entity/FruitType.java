
package com.epam.webparsing.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Fruit-type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Fruit-type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Фруктовое желе"/>
 *     &lt;enumeration value="Сгущенка"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Fruit-type", namespace = "http://www.epam.by/candies")
@XmlEnum
public enum FruitType {

    @XmlEnumValue("\u0424\u0440\u0443\u043a\u0442\u043e\u0432\u043e\u0435 \u0436\u0435\u043b\u0435")
    ФРУКТОВОЕ_ЖЕЛЕ("\u0424\u0440\u0443\u043a\u0442\u043e\u0432\u043e\u0435 \u0436\u0435\u043b\u0435"),
    @XmlEnumValue("\u0421\u0433\u0443\u0449\u0435\u043d\u043a\u0430")
    СГУЩЕНКА("\u0421\u0433\u0443\u0449\u0435\u043d\u043a\u0430");
    private final String value;

    FruitType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FruitType fromValue(String v) {
        for (FruitType c: FruitType.values()) {
            if (c.value.equalsIgnoreCase(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    @Override
    public String toString() {
        return "FruitType{"
                + "value='" + value + '\''
                + '}';
    }}
