package com.epam.info_handling.parser;

import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.entity.TextComponentImpl;
import com.epam.info_handling.exception.InvalidIndexException;
import com.epam.info_handling.exception.UnsupportedMethodException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractTextParser {
    static final Logger LOGGER = LogManager
            .getLogger(AbstractTextParser.class);
    static final String INVALID_INDEX_MSG = "Invalid component index.";
    static final String INVALID_METHOD_MSG = "Trying to call invalid method.";

    private AbstractTextParser next;
    private TextComponent entireTextComponent;

    public TextComponent getParsedText() {
        return entireTextComponent;
    }

    public void parseText(final String fullText) {
        entireTextComponent = new TextComponentImpl();
        try {
            entireTextComponent.add(new TextComponentImpl("Text", fullText));
            invokeNext(entireTextComponent.getChild(0));
        } catch (InvalidIndexException e) {
            LOGGER.error(INVALID_INDEX_MSG);
        } catch (UnsupportedMethodException e) {
            LOGGER.error(INVALID_METHOD_MSG, e);
        }
    }

    public void setNext(final AbstractTextParser nextParser) {
        this.next = nextParser;
    }

    protected abstract void parseComponent(TextComponent textComponent);

    void parseComponent(final TextComponent textComponent,
                                  final String parsePatterRegex,
                                  final String componentName) {
        List<String> components = Arrays.stream(
                textComponent.getData().split(parsePatterRegex))
                .map(String::trim)
                .collect(Collectors.toList());

        int counter = 0;
        for (String component : components) {
            try {
                textComponent.add(new TextComponentImpl(componentName
                        + " " + ++counter, component));
            } catch (UnsupportedMethodException e) {
                LOGGER.error(INVALID_METHOD_MSG, e);
            }
        }

        counter = -1;
        while (++counter < components.size()) {
            try {
                invokeNext(textComponent.getChild(counter));
            } catch (InvalidIndexException e) {
                LOGGER.error(INVALID_INDEX_MSG, e);
            } catch (UnsupportedMethodException e) {
                LOGGER.error(INVALID_METHOD_MSG, e);
            }
        }
    }

    void invokeNext(final TextComponent textComponent) {
        if (next != null) {
            next.parseComponent(textComponent);
        }
    }
}
