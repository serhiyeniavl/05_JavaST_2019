package com.epam.info_handling.parser;

import com.epam.info_handling.entity.Leaf;
import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.entity.TextComponentImpl;
import com.epam.info_handling.exception.InvalidIndexException;
import com.epam.info_handling.exception.UnsupportedMethodException;

public class LexemStructureParser extends AbstractTextParser {

    @Override
    protected void parseComponent(final TextComponent textComponent) {
        String lexem = textComponent.getData();
        try {
            if (lexem.matches("[^a-zA-Z]+")) {
                textComponent.add(new Leaf("Expression", lexem));
            } else if (lexem.matches("[[()\'\"-]?a-zA-Z]+")) {

                textComponent.add(new TextComponentImpl("Word", lexem));
                invokeNext(textComponent.getChild(0));
            } else {
                String wordPart = lexem.substring(0, lexem.length() - 1);
                String punctuationMark = lexem.substring(lexem.length() - 1);
                textComponent.add(new TextComponentImpl("Word", wordPart));
                invokeNext(textComponent.getChild(0));
                textComponent.add(new Leaf("Punctuation mark",
                        punctuationMark));
            }
        } catch (UnsupportedMethodException e) {
            LOGGER.error(INVALID_METHOD_MSG, e);
        } catch (InvalidIndexException e) {
            e.printStackTrace();
        }
    }
}
