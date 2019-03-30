
package com.epam.webparsing.entity;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the mypackage package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {
    private static final String NAMESPACE_URL = "http://www.epam.by/candies";

    private static final QName _FruitCandie_QNAME = new QName(NAMESPACE_URL,
            "fruit-candie");
    private static final QName _ChocolateCandie_QNAME = new QName(NAMESPACE_URL,
            "chocolate-candie");
    private static final QName _Candie_QNAME = new QName(NAMESPACE_URL,
            "candie");

    /**
     * Create an instance of {@link ChocolateCandie }
     */
    public ChocolateCandie createChocolateCandie() {
        return new ChocolateCandie();
    }

    /**
     * Create an instance of {@link FruitCandie }
     */
    public FruitCandie createFruitCandie() {
        return new FruitCandie();
    }

    /**
     * Create an instance of {@link Ingredients }
     */
    public Ingredients createIngredients() {
        return new Ingredients();
    }

    /**
     * Create an instance of {@link Value }
     */
    public Value createValue() {
        return new Value();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FruitCandie }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.epam.by/candies", name = "fruit-candie", substitutionHeadNamespace = "http://www.epam.by/candies", substitutionHeadName = "candie")
    public JAXBElement<FruitCandie> createFruitCandie(FruitCandie value) {
        return new JAXBElement<>(_FruitCandie_QNAME, FruitCandie.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChocolateCandie }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.epam.by/candies", name = "chocolate-candie", substitutionHeadNamespace = "http://www.epam.by/candies", substitutionHeadName = "candie")
    public JAXBElement<ChocolateCandie> createChocolateCandie(ChocolateCandie value) {
        return new JAXBElement<>(_ChocolateCandie_QNAME, ChocolateCandie.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Candie }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.epam.by/candies", name = "candie")
    public JAXBElement<Candie> createCandie(Candie value) {
        return new JAXBElement<>(_Candie_QNAME, Candie.class, null, value);
    }

}
