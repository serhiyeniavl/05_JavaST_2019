package com.epam.webparsing.builder;

import com.epam.webparsing.builder.helper.CandieHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Parser based on SAX model parsing.
 */
public class SAXBuilder extends ParserBuilder {
    /**
     * Class helper for SAXParser..
     */
    private CandieHandler candieHandler;
    /**
     * SAX parser.
     */
    private SAXParser saxParser;

    /**
     * Constructor - initializes candieHandler and creates SAX parser.
     */
    public SAXBuilder() {
        candieHandler = new CandieHandler();
        try {
            saxParser = SAXParserFactory.newInstance().newSAXParser();
        } catch (SAXException e) {
            LOGGER.error("SAX parser error.", e);
        } catch (ParserConfigurationException e) {
            LOGGER.error("SAX parser configuration error.", e);
        }
    }

    /**
     * Creates whole candie list. Calls method parse in SAX parser, it involves
     * candieHandler.
     *
     * @param fileName file path.
     */
    @Override
    public void buildCandies(final String fileName) {
        try {
            saxParser.parse(fileName, candieHandler);
        } catch (IOException e) {
            LOGGER.error("IO error.", e);
        } catch (SAXException e) {
            LOGGER.error("SAX parser error.", e);
        }
        addCandies(candieHandler.getCandies());
    }
}
