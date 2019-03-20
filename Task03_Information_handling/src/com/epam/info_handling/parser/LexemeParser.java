package com.epam.info_handling.parser;

import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.entity.TextElement;

public class LexemeParser extends AbstractTextParser {
    private static final String LEXEME_SEPARATOR_REGEX = "\\s";
    private static final TextElement ELEMENT = TextElement.LEXEME;

    @Override
    protected void parseComponent(final TextComponent textComponent,
                                  final String data) {
        parseComponent(textComponent, LEXEME_SEPARATOR_REGEX, ELEMENT,
                data);
    }
}
