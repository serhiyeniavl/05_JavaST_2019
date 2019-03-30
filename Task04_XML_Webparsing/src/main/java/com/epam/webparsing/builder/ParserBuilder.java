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

public abstract class ParserBuilder {
    protected static final Logger LOGGER = LogManager
            .getLogger(ParserBuilder.class);

    protected List<Candie> candies;
    protected ObjectFactory objectFactory;

    public ParserBuilder() {
        candies = new ArrayList<>();
        objectFactory = new ObjectFactory();
    }

    public List<Candie> getCandies() {
        return new ArrayList<>(candies);
    }

    Candie buildCandie(final Candie candie,
                     final XMLStreamReader xmlStreamReader) throws XMLStreamException {
        return null;
    }

    void buildCandie(final Candie candie, final Element candieElement) {

    }

    public abstract void buildCandies(final String fileName);
}
