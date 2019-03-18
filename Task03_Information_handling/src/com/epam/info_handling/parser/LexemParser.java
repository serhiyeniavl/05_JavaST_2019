package com.epam.info_handling.parser;

import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.entity.TextElement;

public class LexemParser extends AbstractTextParser {
    private static final String LEXEM_SEPARATOR_REGEX = "[\\s\\n]";
    private static final TextElement ELEMENT = TextElement.LEXEM;

    @Override
    protected void parseComponent(final TextComponent textComponent,
                                  final String data) {
        parseComponent(textComponent, LEXEM_SEPARATOR_REGEX, ELEMENT,
                data);
    }
}
