package com.epam.webparsing.builder.helper;

import com.epam.webparsing.entity.Candie;
import com.epam.webparsing.entity.ChocolateCandie;
import com.epam.webparsing.entity.ChocolateType;
import com.epam.webparsing.entity.FruitCandie;
import com.epam.webparsing.entity.FruitType;
import com.epam.webparsing.xml_tag.CandieEnum;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Class helper for SAXParser. Inherit class {@link DefaultHandler}.
 *
 * @see DefaultHandler
 */
public class CandieHandler extends DefaultHandler {
    /**
     * Candies from xml file.
     */
    private List<Candie> candies;
    /**
     * Current observable candie.
     */
    private Candie current;
    /**
     * Current element.
     */
    private CandieEnum currentEnum;
    /**
     * Tags that have a text. Simple xml elements.
     */
    private EnumSet<CandieEnum> withText;

    /**
     * Constructor - initialize candies and enum set with text.
     */
    public CandieHandler() {
        candies = new ArrayList<>();
        withText = EnumSet.range(CandieEnum.ENERGY, CandieEnum.FRUIT_TYPE);
    }

    /**
     * Method calls and check if pointer is in start teg.
     *
     * @param uri        namespace uri.
     * @param localName  current tag name.
     * @param qName      uri.
     * @param attributes tag attributes.
     */
    @Override
    public void startElement(final String uri, final String localName,
                             final String qName, final Attributes attributes) {
        if ("chocolate-candie".equals(qName)) {
            current = new ChocolateCandie();
            initCandie(attributes);

        } else if ("fruit-candie".equals(qName)) {
            current = new FruitCandie();
            initCandie(attributes);
        } else {
            if ("chocolate-type".equals(qName)) {
                currentEnum = CandieEnum.CHOCOLATE_TYPE;
            } else if ("fruit-type".equals(qName)) {
                currentEnum = CandieEnum.FRUIT_TYPE;
            } else {
                CandieEnum temp = CandieEnum.valueOf(qName.toUpperCase());
                if (withText.contains(temp)) {
                    currentEnum = temp;
                }
            }
        }
    }

    /**
     * Method calls and check if pointer is in last teg.
     *
     * @param uri       namespace uri.
     * @param localName current tag name.
     * @param qName     uri.
     */
    @Override
    public void endElement(final String uri, final String localName,
                           final String qName) {
        if ("chocolate-candie".equals(qName)
                || "fruit-type".equals(qName)) {
            candies.add(current);
        }
    }

    /**
     * Method calls when program is observing inner tag.
     *
     * @param ch     tag characters.
     * @param start  first char pos.
     * @param length last char pos.
     */
    @Override
    public void characters(final char[] ch, final int start, final int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case ENERGY:
                    current.setEnergy(Integer.valueOf(s));
                    break;
                case WATER:
                    current.getIngredients().setWater(Integer.valueOf(s));
                    break;
                case SUGAR:
                    current.getIngredients().setSugar(Integer.valueOf(s));
                    break;
                case FRUCTOSE:
                    current.getIngredients().setFructose(Integer.valueOf(s));
                    break;
                case VANILLIN:
                    current.getIngredients().setVanillin(s);
                    break;
                case PROTEIN:
                    current.getValue().setProtein(Double.valueOf(s));
                    break;
                case CARBOHYDRATES:
                    current.getValue().setCarbohydrates(Double.valueOf(s));
                    break;
                case FATS:
                    current.getValue().setFats(Double.valueOf(s));
                    break;
                case DATE:
                    current.setDate(s);
                    break;
                case CHOCOLATE_TYPE:
                    current.setChocolateType(ChocolateType.fromValue(s));
                    break;
                case FRUIT_TYPE:
                    current.setFruitType(FruitType.fromValue(s));
                    break;
                default:
            }
        }
        currentEnum = null;
    }

    /**
     * @return list of candies.
     */
    public List<Candie> getCandies() {
        return candies;
    }

    /**
     * Initializes common params in candie.
     *
     * @param attributes candie attrs.
     */
    private void initCandie(final Attributes attributes) {
        current.setProduction(attributes.getValue("production"));
        current.setName(attributes.getValue("name"));
        final int maxAttrsLen = 3;
        if (attributes.getLength() == maxAttrsLen) {
            current.setFilling(attributes.getValue("filling"));
        }
    }
}
