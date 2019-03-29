package com.epam.webparsing.builder;

import com.epam.webparsing.builder.helper.CandieHandler;
import com.epam.webparsing.entity.Candie;
import org.w3c.dom.Element;
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
    Candie buildCandie(Candie candie, Element candieElement) {
        return null;
    }

    @Override
    public void buildCandies(String fileName) {
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
