package com.epam.info_handling.parser;

import com.epam.info_handling.entity.TextComponent;

public class SentenceParser extends AbstractTextParser {
    private static final String SENTENCE_SEPARATOR_REGEX = "[!.?\"...\"]";
    private static final String COMPONENT_NAME = "Sentence";

    @Override
    public void parseComponent(final TextComponent textComponent) {
        parseComponent(textComponent, SENTENCE_SEPARATOR_REGEX, COMPONENT_NAME);
    }
}
