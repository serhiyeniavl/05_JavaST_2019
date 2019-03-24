package com.epam.info_handling.parser;

import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.entity.TextElement;

/**
 * Class that text into paragraphs.
 */
public class ParagraphParser extends AbstractTextParser {
    /**
     * Regex to parse.
     */
    private static final String PARAGRAPH_SEPARATOR_REGEX = "(?m)(?=^\\s{4})";
    /**
     * Text element to set when parse.
     */
    private static final TextElement ELEMENT = TextElement.PARAGRAPH;

    /**
     * Calls parse method from abstract class based on paragraphs regex.
     *
     * @param textComponent text component.
     * @param data          data to parse.
     */
    @Override
    public void parseComponent(final TextComponent textComponent,
                               final String data) {
        parseComponent(textComponent, PARAGRAPH_SEPARATOR_REGEX,
                ELEMENT, data);
    }
}

