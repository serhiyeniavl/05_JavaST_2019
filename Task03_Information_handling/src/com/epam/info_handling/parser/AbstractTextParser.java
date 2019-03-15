package com.epam.info_handling.parser;

import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.entity.TextComponentImpl;
import com.epam.info_handling.exception.InvalidIndexException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractTextParser {
    static final Logger LOGGER = LogManager
            .getLogger(AbstractTextParser.class);
    static final String ERROR_MESSAGE = "Invalid component index.";

    private AbstractTextParser next;
    private TextComponent textComponent;
    String initialText;

    public TextComponent getParsedText() {
        return textComponent;
    }

    public void parseText(final String text) {
        textComponent = new TextComponentImpl();
        initialText = text;
        parseComponent(textComponent);
    }

    public void setNext(final AbstractTextParser nextParser) {
        this.next = nextParser;
    }

    protected abstract void parseComponent(final TextComponent textComponent);

    void parseComponent(final TextComponent textComponent,
                                  final String PARSE_PATTERN_REGEX,
                                  final String componentName) {
        List<String> components = Arrays.stream(
                textComponent.getData().split(PARSE_PATTERN_REGEX))
                .map(String::trim)
                .collect(Collectors.toList());

        int counter = 0;
        for (String component : components) {
            textComponent.add(new TextComponentImpl(componentName + " "
                    + ++counter, component));
        }

        counter = -1;
        while (++counter < components.size()) {
            try {
                invokeNext(textComponent.getChild(counter));
            } catch (InvalidIndexException e) {
                LOGGER.error(ERROR_MESSAGE);
            }
        }
    }

    void invokeNext(final TextComponent textComponent) {
        if (next != null) {
            next.parseComponent(textComponent);
        }
    }
}
