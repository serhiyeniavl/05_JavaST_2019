package com.epam.info_handling.parser;

import com.epam.info_handling.entity.Leaf;
import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.entity.TextComponentImpl;
import com.epam.info_handling.entity.TextElement;
import com.epam.info_handling.exception.InvalidIndexException;
import com.epam.info_handling.exception.UnsupportedMethodException;

public class LexemStructureParser extends AbstractTextParser {

    @Override
    protected void parseComponent(final TextComponent textComponent,
                                  final String data) {
        String lexem = data;
        try {
            if (lexem.matches("[^a-zA-Z]+")) {
                textComponent.add(new Leaf(lexem, TextElement.EXPRESSION));
            } else if (lexem.matches("[[()\'\"-]?a-zA-Z]+")) {
                textComponent.add(new TextComponentImpl(TextElement.WORD));
                invokeNext(textComponent.getChild(0), lexem);
            } else {
                String wordPart = lexem.substring(0, lexem.length() - 1);
                String punctuationMark = lexem.substring(lexem.length() - 1);
                textComponent.add(new TextComponentImpl(TextElement.WORD));
                invokeNext(textComponent.getChild(0), wordPart);
                textComponent.add(new Leaf(punctuationMark,
                        TextElement.PUNCTUATION_MARK));
            }
        } catch (UnsupportedMethodException e) {
            LOGGER.error(INVALID_METHOD_MSG, e);
        } catch (InvalidIndexException e) {
            LOGGER.error(INVALID_INDEX_MSG, e);
        }
    }
}
