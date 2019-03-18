package com.epam.info_handling.parser;

import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.entity.TextElement;

public class SentenceParser extends AbstractTextParser {
    private static final String SENTENCE_SEPARATOR_REGEX = "[!.?\"...\"]";
    private static final TextElement ELEMENT = TextElement.SENTENCE;

    @Override
    public void parseComponent(final TextComponent textComponent,
                               final String data) {
        parseComponent(textComponent, SENTENCE_SEPARATOR_REGEX, ELEMENT,
                data);
    }
}
