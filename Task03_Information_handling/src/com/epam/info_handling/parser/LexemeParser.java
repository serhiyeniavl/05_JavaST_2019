package com.epam.info_handling.parser;

import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.entity.TextElement;

/**
 * Class parse that sentence into lexemes.
 */
public class LexemeParser extends AbstractTextParser {
    /**
     * Regex to parse.
     */
    private static final String LEXEME_SEPARATOR_REGEX = "\\s";
    /**
     * Text element to set when parse.
     */
    private static final TextElement ELEMENT = TextElement.LEXEME;

    /**
     * Calls parse method from abstract class based on lexemes regex.
     *
     * @param textComponent text component.
     * @param data          data to parse.
     */
    @Override
    protected void parseComponent(final TextComponent textComponent,
                                  final String data) {
        parseComponent(textComponent, LEXEME_SEPARATOR_REGEX, ELEMENT,
                data);
    }
}
