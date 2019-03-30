package com.epam.webparsing.builder;

import com.epam.webparsing.entity.Candie;
import com.epam.webparsing.entity.ObjectFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract parser builder. Contains common logger, candies list and
 * object factory.
 */
public abstract class ParserBuilder {
    /**
     * Logger.
     */
    static final Logger LOGGER = LogManager
            .getLogger(ParserBuilder.class);

    /**
     * List of candies.
     */
    private List<Candie> candies;
    /**
     * Object factory. Creates entities form xml file.
     */
    static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();

    /**
     * Constructor - initialize candies list.
     */
    ParserBuilder() {
        candies = new ArrayList<>();
    }

    /**
     * @return list of candies.
     */
    public List<Candie> getCandies() {
        return new ArrayList<>(candies);
    }

    /**
     * Adds candies to candies list.
     * @param candie candies to add.
     */
    protected void addCandie(final Candie candie) {
        candies.add(candie);
    }

    /**
     * Add whole list to candies.
     * @param candieList list to add.
     */
    protected void addCandies(final List<Candie> candieList) {
        candies.addAll(candieList);
    }

    /**
     * Empty method. Should be realized in successor class.
     * @param candie candie to create.
     * @param xmlStreamReader xml reader for StAX parser.
     * @throws XMLStreamException when tag in invalid.
     */
    void buildCandie(final Candie candie,
                     final XMLStreamReader xmlStreamReader)
            throws XMLStreamException {
    }

    /**
     * Empty method. Should be realized in successor class.
     * @param candie candie to create.
     * @param candieElement candie element.
     */
    void buildCandie(final Candie candie, final Element candieElement) {
    }

    /**
     * Builds whole list of candies.
     * @param fileName file path.
     */
    public abstract void buildCandies(String fileName);
}
