package com.epam.info_handling.parser;

import com.epam.info_handling.entity.TextComponent;

public class SymbolParser extends AbstractTextParser {
    private static final String SYMBOL_SEPARATOR_REGEX = "";
    private static final String COMPONENT_NAME = "Symbol";

    @Override
    protected void parseComponent(TextComponent textComponent) {
        parseComponent(textComponent, SYMBOL_SEPARATOR_REGEX, COMPONENT_NAME);
    }
}
