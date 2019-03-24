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

/**
 * Class that sorts each sentence by lexemes length it has.
 */
public class WordLengthSortSpecification implements SortSpecification {
    /**
     * Logger for logging exceptions.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(WordLengthSortSpecification.class);
    /**
     * Error message when method is not support.
     */
    private static final String ERROR_MSG
            = "Error when try to get components from text.";

    /**
     * Sorts each sentence by lexemes length it has.
     *
     * @param components text components to sort.
     * @return list of sorted text components.
     */
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

    /**
     * Sorts lexemes in sentence by length.
     *
     * @param sentence sentence for sort.
     * @return sorted lexemes.
     */
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

    /**
     * @param components text components to sort.
     * @param symbol     symbol to define.
     * @return empty list.
     */
    @Override
    public List<TextComponent> sort(final TextComponent components,
                                    final char symbol) {
        return new ArrayList<>();
    }
}
