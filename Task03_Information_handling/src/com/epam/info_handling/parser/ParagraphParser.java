package com.epam.info_handling.parser;

import com.epam.info_handling.entity.TextComponent;

public class ParagraphParser extends AbstractTextParser {
    private static final String PARAGRAPH_SEPARATOR_REGEX = "(?m)(?=^\\s{4})";
    private static final String COMPONENT_NAME = "Paragraph";

    @Override
    public void parseComponent(final TextComponent textComponent) {
        parseComponent(textComponent, PARAGRAPH_SEPARATOR_REGEX,
                COMPONENT_NAME);
    }
}

