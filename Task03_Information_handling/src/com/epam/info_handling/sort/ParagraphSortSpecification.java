package com.epam.info_handling.sort;

import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.exception.UnsupportedMethodException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that stores method for sort text component by sentences in each
 * paragraph.
 */
public class ParagraphSortSpecification implements SortSpecification {
    /**
     * Logger for logging exceptions.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ParagraphSortSpecification.class);

    /**
     * Sorts text component by sentences in each paragraph.
     * @param textComponent text component to sort.
     * @return list of sorted paragraphs.
     */
    @Override
    public List<TextComponent> sort(final TextComponent textComponent) {
        try {
            List<TextComponent> paragraphs = textComponent.getComponents();
            paragraphs.sort((o1, o2) -> {
                try {
                    return o1.getComponents().size()
                            - o2.getComponents().size();
                } catch (UnsupportedMethodException e) {
                    LOGGER.error(
                            "Error when try to get components from text.", e);
                }
                return 0;
            });
            return paragraphs;
        } catch (UnsupportedMethodException e) {
            LOGGER.error(
                    "Error when try to get components from text.", e);
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
