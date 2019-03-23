package com.epam.info_handling.sort;

import com.epam.info_handling.action.TextCollector;
import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.entity.TextElement;
import com.epam.info_handling.exception.InvalidIndexException;
import com.epam.info_handling.exception.UnsupportedMethodException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WordLengthSortSpecification implements SortSpecification {
    private static final Logger LOGGER
            = LogManager.getLogger(WordLengthSortSpecification.class);
    private static final String ERROR_MSG
            = "Error when try to get components from text.";

    @Override
    public List<TextComponent> sort(final TextComponent components) {
        TextCollector textCollector = new TextCollector();
        List<TextComponent> sentences
                = textCollector.acquireSentences(components);
        for (TextComponent sentence : sentences) {
            try {
                sentence.add(sortByLen(sentence));
            } catch (UnsupportedMethodException e) {
                LOGGER.error(ERROR_MSG, e);
            }
        }
        return sentences;
    }

    private List<TextComponent> sortByLen(final TextComponent sentence) {
        try {
            List<TextComponent> lexemes = sentence.getComponents();
            sentence.remove(lexemes);
            lexemes.sort(Comparator.comparingInt(lexeme -> {
                int wordLen = 0;
                try {
                    TextComponent lexemeInstance = lexeme.getChild(0);
                    if (lexemeInstance.getTextElement()
                            != TextElement.PUNCTUATION_MARK) {
                        wordLen = lexemeInstance.toString().length();
                    }
                } catch (InvalidIndexException e) {
                    LOGGER.error("Invalid index when call getChild.", e);
                } catch (UnsupportedMethodException e) {
                    LOGGER.error(
                            ERROR_MSG, e);
                }
                return wordLen;
            }));
            return lexemes;
        } catch (UnsupportedMethodException e) {
            LOGGER.error(
                    ERROR_MSG, e);
        }
        return new ArrayList<>();
    }

    @Override
    public List<TextComponent> sort(final TextComponent components,
                                    final char symbol) {
        return new ArrayList<>();
    }
}
