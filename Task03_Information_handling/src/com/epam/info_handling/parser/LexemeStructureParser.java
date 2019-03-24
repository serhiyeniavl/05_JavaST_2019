package com.epam.info_handling.parser;

import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.entity.TextComponentImpl;
import com.epam.info_handling.entity.TextElement;
import com.epam.info_handling.exception.InvalidIndexException;
import com.epam.info_handling.exception.UnsupportedMethodException;

/**
 * Class for parse lexeme into three component - expression, word and
 * punctuation mark.
 */
public class LexemeStructureParser extends AbstractTextParser {
    /**
     * Regex to check punctuation mark.
     */
    private static final String PUNCTUATION_SEPARATOR_REGEX = "[^!;:.?]+";
    /**
     * Regexp to define expression.
     */
    private static final String EXPRESSION_SEPARATOR_REGEX = "[0-9&^~|<>()]+";

    /**
     * Overriden method, that divides lexeme into three component. If defined
     * an expression - add to text component, also - word. In both cases checks
     * on punctuation marks.
     *
     * @param textComponent text component.
     * @param data          data to parse.
     */
    @Override
    protected void parseComponent(final TextComponent textComponent,
                                  final String data) {
        try {
            String[] punctuationMarks = acquirePunctuationMarksFromLexeme(data);
            if (data.matches(EXPRESSION_SEPARATOR_REGEX)
                    && punctuationMarks.length == 0) {
                textComponent.add(
                        new TextComponentImpl(TextElement.EXPRESSION));
                invokeNext(textComponent.getChild(0), data);
            } else {
                if (punctuationMarks.length == 0) {
                    textComponent.add(new TextComponentImpl(TextElement.WORD));
                    invokeNext(textComponent.getChild(0), data);
                } else {
                    String punctuationMark = punctuationMarks[1];
                    String word = data.substring(
                            0, data.length() - punctuationMark.length());
                    textComponent.add(new TextComponentImpl(TextElement.WORD));
                    invokeNext(textComponent.getChild(0), word);
                    textComponent.add(
                            new TextComponentImpl(
                                    TextElement.PUNCTUATION_MARK));
                    invokeNext(textComponent.getChild(1), punctuationMark);
                }
            }
        } catch (UnsupportedMethodException e) {
            LOGGER.error(INVALID_METHOD_MSG, e);
        } catch (InvalidIndexException e) {
            LOGGER.error(INVALID_INDEX_MSG, e);
        }
    }

    /**
     * @param lexeme lexeme to define punctuation mark in it.
     * @return punctuation marks from lexeme.
     */
    private String[] acquirePunctuationMarksFromLexeme(final String lexeme) {
        return lexeme.split(PUNCTUATION_SEPARATOR_REGEX);
    }
}
