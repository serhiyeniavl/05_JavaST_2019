package com.epam.info_handling.parser;

import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.entity.TextElement;

public class ParagraphParser extends AbstractTextParser {
    private static final String PARAGRAPH_SEPARATOR_REGEX = "(?m)(?=^\\s{4})";
    private static final TextElement ELEMENT = TextElement.PARAGRAPH;

    @Override
    public void parseComponent(final TextComponent textComponent,
                               final String data) {
        parseComponent(textComponent, PARAGRAPH_SEPARATOR_REGEX,
                ELEMENT, data);
    }
}

