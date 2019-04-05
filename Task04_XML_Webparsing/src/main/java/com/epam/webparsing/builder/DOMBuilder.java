package com.epam.webparsing.builder;

import com.epam.webparsing.entity.Candie;
import com.epam.webparsing.entity.ChocolateCandie;
import com.epam.webparsing.entity.ChocolateType;
import com.epam.webparsing.entity.FruitCandie;
import com.epam.webparsing.entity.FruitType;
import com.epam.webparsing.entity.Ingredients;
import com.epam.webparsing.entity.Value;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Parser based on DOM model parsing.
 */
public class DOMBuilder extends ParserBuilder {
    /**
     * API to obtain DOM Document instances.
     */
    private DocumentBuilder documentBuilder;

    /**
     * Constructor - initializes document builder.
     */
    public DOMBuilder() {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory
                .newInstance();
        try {
            documentBuilder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error("Configuration parser error.", e);
        }
    }

    /**
     * Builds candie. Creates common tags values from abstract candie.
     *
     * @param candie        candie to create.
     * @param candieElement candie element.
     */
    @Override
    void buildCandie(final Candie candie, final Element candieElement) {
        candie.setProduction(candieElement.getAttribute("production"));
        candie.setName(candieElement.getAttribute("name"));
        if (!candieElement.getAttribute("filling").isEmpty()) {
            candie.setFilling(candieElement.getAttribute("filling"));
        }
        candie.setEnergy(Integer.valueOf(
                getElementTextContent(candieElement, "energy")));
        try {
            candie.setDate(new SimpleDateFormat("yyyy-MM-dd")
                    .parse(getElementTextContent(candieElement, "date")));
        } catch (ParseException e) {
            LOGGER.error("Date parse error", e);
        }
        Element ingredientsElement = (Element) candieElement
                .getElementsByTagName("ingredients").item(0);
        Ingredients ingredients = OBJECT_FACTORY.createIngredients();
        ingredients.setFructose(
                Integer.valueOf(getElementTextContent(
                        ingredientsElement, "fructose")));
        ingredients.setSugar(
                Integer.valueOf(getElementTextContent(
                        ingredientsElement, "sugar")));
        ingredients.setVanillin(
                getElementTextContent(ingredientsElement, "vanillin"));
        ingredients.setWater(Integer.valueOf(getElementTextContent(
                ingredientsElement, "water")));
        candie.setIngredients(ingredients);
        Value value = OBJECT_FACTORY.createValue();
        value.setCarbohydrates(
                Double.valueOf(getElementTextContent(
                        candieElement, "carbohydrates")));
        value.setProtein(
                Double.valueOf(getElementTextContent(
                        candieElement, "protein")));
        value.setFats(
                Double.valueOf(getElementTextContent(
                        candieElement, "fats")));
        candie.setValue(value);
    }

    /**
     * Builds chocolate candie, adds specific tags values to candie.
     *
     * @param candieElement current element.
     * @return chocolate candie.
     */
    private Candie buildChocolateCandie(final Element candieElement) {
        ChocolateCandie candie = OBJECT_FACTORY.createChocolateCandie();
        buildCandie(candie, candieElement);
        candie.setChocolateType(
                ChocolateType.fromValue(
                        getElementTextContent(
                                candieElement, "chocolate-type")));
        return candie;
    }

    /**
     * Builds fruit candie, adds specific tags values to candie.
     *
     * @param candieElement current element.
     * @return fruit candie.
     */
    private Candie buildFruitCandie(final Element candieElement) {
        FruitCandie candie = OBJECT_FACTORY.createFruitCandie();
        buildCandie(candie, candieElement);
        candie.setFruitType(
                FruitType.fromValue(
                        getElementTextContent(
                                candieElement, "fruit-type")));
        return candie;
    }

    /**
     * Builds whole list of candies.
     *
     * @param fileName file path.
     */
    @Override
    public void buildCandies(final String fileName) {
        Document document;
        try {
            document = documentBuilder.parse(fileName);
            Element root = document.getDocumentElement();
            NodeList chocolateCandiesList = root
                    .getElementsByTagName("chocolate-candie");
            NodeList fruitCandiesList = root
                    .getElementsByTagName("fruit-candie");
            for (int i = 0; i < chocolateCandiesList.getLength(); i++) {
                Element candieElement = (Element) chocolateCandiesList.item(i);
                addCandie(buildChocolateCandie(candieElement));
            }
            for (int i = 0; i < fruitCandiesList.getLength(); i++) {
                Element candieElement = (Element) fruitCandiesList.item(i);
                addCandie(buildFruitCandie(candieElement));
            }
        } catch (SAXException e) {
            LOGGER.error("File error or I/O error.", e);
        } catch (IOException e) {
            LOGGER.error("Parsing failure.", e);
        }
    }

    /**
     * @param candieElement current element.
     * @param elementName   tag name.
     * @return current tag content.
     */
    private static String getElementTextContent(final Element candieElement,
                                                final String elementName) {
        return candieElement.getElementsByTagName(elementName)
                .item(0)
                .getTextContent();
    }
}
