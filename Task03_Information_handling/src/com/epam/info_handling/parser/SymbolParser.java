package com.epam.info_handling.parser;

import com.epam.info_handling.entity.Leaf;
import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.exception.UnsupportedMethodException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SymbolParser extends AbstractTextParser {
    private static final String SYMBOL_SEPARATOR_REGEX = "";
    private static final String COMPONENT_NAME = "Symbol";

    @Override
    protected void parseComponent(final TextComponent textComponent) {
        List<String> components = Arrays.stream(
                textComponent.getData().split(SYMBOL_SEPARATOR_REGEX))
                .map(String::trim)
                .collect(Collectors.toList());

        int counter = 0;
        for (String component : components) {
            try {
                textComponent.add(new Leaf(COMPONENT_NAME
                        + " " + ++counter, component));
            } catch (UnsupportedMethodException e) {
                LOGGER.error(INVALID_METHOD_MSG, e);
            }
        }
    }
}
