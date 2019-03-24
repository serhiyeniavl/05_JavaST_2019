package com.epam.info_handling.parser;

import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.entity.TextComponentImpl;
import com.epam.info_handling.entity.TextElement;
import com.epam.info_handling.exception.InvalidIndexException;
import com.epam.info_handling.exception.UnsupportedMethodException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract class that defined pattern Chain of Responsibility.
 */
public abstract class AbstractTextParser {
    /**
     * Logger for logging exceptions.
     */
    static final Logger LOGGER = LogManager
            .getLogger(AbstractTextParser.class);
    /**
     * Error message when index is invalid.
     */
    static final String INVALID_INDEX_MSG = "Invalid component index.";
    /**
     * Error message when method does not support by class.
     */
    static final String INVALID_METHOD_MSG = "Trying to call invalid method.";

    /**
     * Reference to the next parser.
     */
    private AbstractTextParser next;
    /**
     * Text component chat parsers create.
     */
    private TextComponent entireTextComponent;

    /**
     * @return whole created text component.
     */
    public TextComponent getParsedText() {
        return entireTextComponent;
    }

    /**
     * Starts chain of parsers.
     *
     * @param fullText text to be parsed.
     */
    public void parseText(final String fullText) {
        entireTextComponent = new TextComponentImpl(TextElement.TEXT);
        invokeNext(entireTextComponent, fullText);
    }

    /**
     * Sets next parser.
     *
     * @param nextParser parser to set.
     */
    public void setNext(final AbstractTextParser nextParser) {
        this.next = nextParser;
    }

    /**
     * Method that each parser have to override.
     *
     * @param textComponent text component.
     * @param data          data to parse.
     */
    protected abstract void parseComponent(TextComponent textComponent,
                                           String data);

    /**
     * Parses text component and invoke next parser on parsed data.
     *
     * @param textComponent     text component.
     * @param parsePatternRegex regex by it data will be split.
     * @param element           text element.
     * @param data              data to split and parse.
     */
    void parseComponent(final TextComponent textComponent,
                        final String parsePatternRegex,
                        final TextElement element,
                        final String data) {
        List<String> components = Arrays.stream(
                data.split(parsePatternRegex))
                .map(String::trim)
                .collect(Collectors.toList());

        int counter = -1;
        while (++counter < components.size()) {
            try {
                textComponent.add(new TextComponentImpl(element));
                invokeNext(textComponent.getChild(counter),
                        components.get(counter));
            } catch (InvalidIndexException e) {
                LOGGER.error(INVALID_INDEX_MSG, e);
            } catch (UnsupportedMethodException e) {
                LOGGER.error(INVALID_METHOD_MSG, e);
            }
        }


    }

    /**
     * Invokes next parser if it exists.
     *
     * @param textComponent text component to parse.
     * @param data          data to parse.
     */
    void invokeNext(final TextComponent textComponent, final String data) {
        if (next != null) {
            next.parseComponent(textComponent, data);
        }
    }
}
