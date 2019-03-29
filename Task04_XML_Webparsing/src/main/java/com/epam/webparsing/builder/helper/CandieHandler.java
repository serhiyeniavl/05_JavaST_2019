package com.epam.webparsing.builder.helper;

import com.epam.webparsing.entity.*;
import com.epam.webparsing.xml_tag.CandieEnum;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class CandieHandler extends DefaultHandler {
    private List<Candie> candies;
    private Candie current;
    private CandieEnum currentEnum;
    private EnumSet<CandieEnum> withText;

    public CandieHandler() {
        candies = new ArrayList<>();
        withText = EnumSet.range(CandieEnum.ENERGY, CandieEnum.FRUIT_TYPE);
    }

    @Override
    public void startElement(final String uri, final String localName,
                             final String qName, final Attributes attributes) {
        if ("chocolate-candie".equals(localName)) {
            current = new ChocolateCandie();
            initCandie(attributes);

        } else if ("fruit-candie".equals(localName)) {
            current = new FruitCandie();
            initCandie(attributes);
        } else {
            if ("chocolate-type".equals(localName)) {
                currentEnum = CandieEnum.CHOCOLATE_TYPE;
            } else if ("fruit-type".equals(localName)) {
                currentEnum = CandieEnum.FRUIT_TYPE;
            } else {
                CandieEnum temp = CandieEnum.valueOf(localName.toUpperCase());
                if (withText.contains(temp)) {
                    currentEnum = temp;
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("chocolate-candie".equals(localName)
                || "fruit-type".equals(localName)) {
            candies.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        Ingredients ingredients = new Ingredients();
        Value value = new Value();
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

    public List<Candie> getCandies() {
        return candies;
    }

    private void initCandie(final Attributes attributes) {
        current.setProduction(attributes.getValue("production"));
        current.setName(attributes.getValue("name"));
        if (attributes.getLength() == 3) {
            current.setFilling(attributes.getValue("filling"));
        }
    }
}
