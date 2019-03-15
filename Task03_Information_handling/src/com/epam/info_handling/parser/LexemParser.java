package com.epam.info_handling.parser;

import com.epam.info_handling.entity.TextComponent;

public class LexemParser extends AbstractTextParser {
    private static final String LEXEM_SEPARATOR_REGEX = "[\\s\\n]";
    private static final String COMPONENT_NAME = "Lexem";

    @Override
    protected void parseComponent(TextComponent textComponent) {
        parseComponent(textComponent, LEXEM_SEPARATOR_REGEX, COMPONENT_NAME);
    }
}
