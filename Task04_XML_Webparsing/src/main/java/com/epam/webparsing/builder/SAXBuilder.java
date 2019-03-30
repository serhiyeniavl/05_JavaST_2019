package com.epam.webparsing.builder;

import com.epam.webparsing.builder.helper.CandieHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class SAXBuilder extends ParserBuilder {
    private CandieHandler candieHandler;
    private XMLReader xmlReader;

    public SAXBuilder() {
        super();
        candieHandler = new CandieHandler();
        try {
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(candieHandler);
        } catch (SAXException e) {
            LOGGER.error("SAX parser error.", e);
        }
    }

    @Override
    public void buildCandies(final String fileName) {
        try {
            xmlReader.parse(fileName);
        } catch (IOException e) {
            LOGGER.error("IO error.", e);
        } catch (SAXException e) {
            LOGGER.error("SAX parser error.", e);
        }
        super.candies = candieHandler.getCandies();
    }
}
