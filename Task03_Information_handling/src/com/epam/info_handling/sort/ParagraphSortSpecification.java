package com.epam.info_handling.sort;

import com.epam.info_handling.action.TextSorter;
import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.exception.UnsupportedMethodException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ParagraphSortSpecification implements SortSpecification {
    private static final Logger LOGGER
            = LogManager.getLogger(ParagraphSortSpecification.class);


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

    @Override
    public List<TextComponent> sort(final TextComponent components,
                                    final char symbol) {
        return new ArrayList<>();
    }
}
