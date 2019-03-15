package com.epam.info_handling.parser;

import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.entity.TextComponentImpl;
import com.epam.info_handling.exception.InvalidIndexException;

public class TextParser extends AbstractTextParser {

    @Override
    public void parseComponent(TextComponent textComponent) {
        textComponent.add(new TextComponentImpl("Text", initialText));
        try {
            invokeNext(textComponent.getChild(0));
        } catch (InvalidIndexException e) {
            LOGGER.error(ERROR_MESSAGE);
        }
    }
}
