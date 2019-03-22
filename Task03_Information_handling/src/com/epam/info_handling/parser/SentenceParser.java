package com.epam.info_handling.parser;

import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.entity.TextComponentImpl;
import com.epam.info_handling.entity.TextElement;
import com.epam.info_handling.exception.InvalidIndexException;
import com.epam.info_handling.exception.UnsupportedMethodException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractTextParser {
    private static final String SENTENCE_SEPARATOR_REGEX
            = "[^.?!]+?(?:\\.{3}|\\.|!{3}|!|\\?)";
    private static final TextElement ELEMENT = TextElement.SENTENCE;

    @Override
    public void parseComponent(final TextComponent textComponent,
                               final String data) {
        Pattern pattern = Pattern.compile(SENTENCE_SEPARATOR_REGEX);
        Matcher matcher = pattern.matcher(data);

        int counter = -1;
        while (matcher.find()) {
            try {
                textComponent.add(new TextComponentImpl(ELEMENT));
                invokeNext(textComponent
                        .getChild(++counter), matcher.group(0).trim());
            } catch (UnsupportedMethodException e) {
                LOGGER.error(INVALID_METHOD_MSG, e);
            } catch (InvalidIndexException e) {
                LOGGER.error(INVALID_INDEX_MSG, e);
            }
        }
    }
}
