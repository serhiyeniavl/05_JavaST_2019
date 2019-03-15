package com.epam.info_handling.parser;

import com.epam.info_handling.entity.TextComponent;

public class WordParser extends AbstractTextParser {
    private static final String WORD_SEPARATOR_REGEX = "[,;:.!?\\s]+";
    private static final String COMPONENT_NAME = "Word";

    @Override
    protected void parseComponent(TextComponent textComponent) {
        parseComponent(textComponent, WORD_SEPARATOR_REGEX, COMPONENT_NAME);
    }
}
