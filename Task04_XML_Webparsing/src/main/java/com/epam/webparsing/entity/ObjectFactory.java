
package com.epam.webparsing.entity;

/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the entity package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
public class ObjectFactory {

    /**
     * Create an instance of {@link ChocolateCandie }.
     *
     * @return new object.
     */
    public ChocolateCandie createChocolateCandie() {
        return new ChocolateCandie();
    }

    /**
     * Create an instance of {@link FruitCandie }.
     *
     * @return new object.
     */
    public FruitCandie createFruitCandie() {
        return new FruitCandie();
    }

    /**
     * Create an instance of {@link Ingredients }.
     *
     * @return new object.
     */
    public Ingredients createIngredients() {
        return new Ingredients();
    }

    /**
     * Create an instance of {@link Value }.
     *
     * @return new object.
     */
    public Value createValue() {
        return new Value();
    }
}
