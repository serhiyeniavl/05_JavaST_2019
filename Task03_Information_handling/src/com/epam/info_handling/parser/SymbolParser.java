package com.epam.info_handling.parser;

import com.epam.info_handling.entity.Leaf;
import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.entity.TextElement;
import com.epam.info_handling.exception.UnsupportedMethodException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to parse components into symbols.
 */
public class SymbolParser extends AbstractTextParser {
    /**
     * Regex to parse.
     */
    private static final String SYMBOL_SEPARATOR_REGEX = "";
    /**
     * Text element to set when parse.
     */
    private static final TextElement ELEMENT = TextElement.SYMBOL;

    /**
     * Method parse data into symbols.
     * @param textComponent text component.
     * @param data          data to parse.
     */
    @Override
    protected void parseComponent(final TextComponent textComponent,
                                  final String data) {
        List<String> components = Arrays.stream(
                data.split(SYMBOL_SEPARATOR_REGEX))
                .map(String::trim)
                .collect(Collectors.toList());

        for (String component : components) {
            try {
                textComponent.add(new Leaf(component.charAt(0), ELEMENT));
            } catch (UnsupportedMethodException e) {
                LOGGER.error(INVALID_METHOD_MSG, e);
            }
        }
    }
}
