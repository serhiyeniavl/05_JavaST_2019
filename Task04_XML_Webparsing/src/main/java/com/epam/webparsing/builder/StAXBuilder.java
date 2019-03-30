package com.epam.webparsing.builder;

import com.epam.webparsing.entity.Candie;
import com.epam.webparsing.entity.ChocolateCandie;
import com.epam.webparsing.entity.ChocolateType;
import com.epam.webparsing.entity.FruitCandie;
import com.epam.webparsing.entity.FruitType;
import com.epam.webparsing.entity.Ingredients;
import com.epam.webparsing.entity.Value;
import com.epam.webparsing.xml_tag.CandieEnum;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Parser based on StAX model parsing.
 */
public class StAXBuilder extends ParserBuilder {
    /**
     * String to identify error msg.
     */
    private static final String ERROR_MSG = "Unknown element in tag.";
    /**
     * @see XMLInputFactory
     */
    private XMLInputFactory xmlInputFactory;

    /**
     * Constructor - initializes {@link XMLInputFactory}.
     */
    public StAXBuilder() {
        xmlInputFactory = XMLInputFactory.newInstance();
    }

    /**
     * Builds whole list of candies.
     * @param fileName file path.
     */
    @Override
    public void buildCandies(final String fileName) {
        XMLStreamReader streamReader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(
                new File(fileName))) {
            streamReader = xmlInputFactory.createXMLStreamReader(inputStream);
            while (streamReader.hasNext()) {
                int type = streamReader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = streamReader.getLocalName();
                    if ("chocolate-candie".equals(name)) {
                        addCandie(buildChocolateCandie(streamReader));
                    } else if ("fruit-candie".equals(name)) {
                        addCandie(buildFruitCandie(streamReader));
                    }
                }
            }
        } catch (XMLStreamException e) {
            LOGGER.error("StAX parsing error.", e);
        } catch (FileNotFoundException e) {
            LOGGER.error("File" + fileName + " not found!", e);
        } catch (IOException e) {
            LOGGER.error("IO error.", e);
        }
    }

    /**
     * Builds chocolate candie, adds specific tags values to candie.
     * @param xmlStreamReader xml reader.
     * @return chocolate candie.
     * @throws XMLStreamException when xml tag in invalid.
     */
    private Candie buildChocolateCandie(final XMLStreamReader xmlStreamReader)
            throws XMLStreamException {
        ChocolateCandie candie = OBJECT_FACTORY.createChocolateCandie();
        buildCandie(candie, xmlStreamReader);
        return candie;
    }

    /**
     * Builds fruit candie, adds specific tags values to candie.
     * @param xmlStreamReader xml reader.
     * @return fruit candie.
     * @throws XMLStreamException when xml tag in invalid.
     */
    private Candie buildFruitCandie(final XMLStreamReader xmlStreamReader)
            throws XMLStreamException {
        FruitCandie candie = OBJECT_FACTORY.createFruitCandie();
        buildCandie(candie, xmlStreamReader);
        return candie;
    }

    /**
     * Builds candie. Creates common tags values from abstract candie.
     *
     * @param candie        candie to create.
     * @param xmlStreamReader xml reader.
     */
    @Override
    void buildCandie(final Candie candie,
                       final XMLStreamReader xmlStreamReader)
            throws XMLStreamException {
        candie.setProduction(xmlStreamReader.getAttributeValue(null,
                "production"));
        candie.setName(xmlStreamReader.getAttributeValue(null,
                "name"));
        if (xmlStreamReader.getAttributeValue(
                null, "filling") != null) {
            candie.setFilling(xmlStreamReader.getAttributeValue(
                    null, "filling"));
        }
        String name;
        while (xmlStreamReader.hasNext()) {
            int type = xmlStreamReader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = xmlStreamReader.getLocalName();
                    if (name.equals("chocolate-type")) {
                        candie.setChocolateType(
                                ChocolateType.fromValue(
                                        getXMLText(xmlStreamReader)));
                        continue;
                    } else if (name.equals("fruit-type")) {
                        candie.setFruitType(
                                FruitType.fromValue(
                                        getXMLText(xmlStreamReader)));
                        continue;
                    }
                    switch (CandieEnum.valueOf(name.toUpperCase())) {
                        case ENERGY:
                            candie.setEnergy(
                                    Integer.valueOf(
                                            getXMLText(xmlStreamReader)));
                            break;
                        case DATE:
                            candie.setDate(getXMLText(xmlStreamReader));
                            break;
                        case INGREDIENTS:
                            candie.setIngredients(
                                    getXMLIngredients(xmlStreamReader));
                            break;
                        case VALUE:
                            candie.setValue(
                                    getXMLValue(xmlStreamReader));
                            break;
                        default:
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = xmlStreamReader.getLocalName();
                    if ("chocolate-candie".equals(name)
                            || "fruit-candie".equals(name)) {
                        return;
                    }
                default:
            }
        }
        throw new XMLStreamException(ERROR_MSG);
    }

    /**
     * @param reader xml reader.
     * @return text form current tag.
     * @throws XMLStreamException when tag in invalid.
     */
    private String getXMLText(final XMLStreamReader reader)
            throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

    /**
     * Read and creates data from tag ingredients.
     * @param xmlStreamReader xml reader.
     * @return created ingredients.
     * @throws XMLStreamException when tag is invalid.
     */
    private Ingredients getXMLIngredients(
            final XMLStreamReader xmlStreamReader) throws XMLStreamException {
        Ingredients ingredients = new Ingredients();
        int type;
        String name;
        while (xmlStreamReader.hasNext()) {
            type = xmlStreamReader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = xmlStreamReader.getLocalName();
                    switch (CandieEnum.valueOf(name.toUpperCase())) {
                        case WATER:
                            ingredients.setWater(
                                    Integer.valueOf(
                                            getXMLText(xmlStreamReader)));
                            break;
                        case SUGAR:
                            ingredients.setSugar(
                                    Integer.valueOf(
                                            getXMLText(xmlStreamReader)));
                            break;
                        case FRUCTOSE:
                            ingredients.setFructose(
                                    Integer.valueOf(
                                            getXMLText(xmlStreamReader)));
                            break;
                        case VANILLIN:
                            ingredients
                                    .setVanillin(getXMLText(xmlStreamReader));
                            break;
                        default:
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = xmlStreamReader.getLocalName();
                    if (CandieEnum.valueOf(name.toUpperCase())
                            == CandieEnum.INGREDIENTS) {
                        return ingredients;
                    }
                    break;
                default:
            }
        }
        throw new XMLStreamException(ERROR_MSG);
    }

    /**
     * Read and creates data from tag value.
     * @param xmlStreamReader xml reader.
     * @return created value.
     * @throws XMLStreamException when tag is invalid.
     */
    private Value getXMLValue(final XMLStreamReader xmlStreamReader)
            throws XMLStreamException {
        Value value = new Value();
        int type;
        String name;
        while (xmlStreamReader.hasNext()) {
            type = xmlStreamReader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = xmlStreamReader.getLocalName();
                    switch (CandieEnum.valueOf(name.toUpperCase())) {
                        case PROTEIN:
                            value.setProtein(
                                    Double.valueOf(
                                            getXMLText(xmlStreamReader)));
                            break;
                        case FATS:
                            value.setFats(
                                    Double.valueOf(
                                            getXMLText(xmlStreamReader)));
                            break;
                        case CARBOHYDRATES:
                            value.setCarbohydrates(
                                    Double.valueOf(
                                            getXMLText(xmlStreamReader)));
                            break;
                        default:
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = xmlStreamReader.getLocalName();
                    if (CandieEnum.valueOf(name.toUpperCase())
                            == CandieEnum.VALUE) {
                        return value;
                    }
                    break;
                default:
            }
        }
        throw new XMLStreamException(ERROR_MSG);
    }
}
