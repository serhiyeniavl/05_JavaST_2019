package com.epam.info_handling.action;

import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.sort.SortSpecification;

import java.util.List;

/**
 * Class for processing queries to sort text component.
 */
public class TextSorter {

    /**
     * Calls specified sort method on received sort specification.
     *
     * @param sortSpecification specified sort.
     * @param textComponent     component to sort.
     * @return list of sorted components.
     */
    public List<TextComponent> query(final SortSpecification sortSpecification,
                                     final TextComponent textComponent) {
        return sortSpecification.sort(textComponent);
    }

    /**
     * Calls specified sort method on received sort specification.
     *
     * @param sortSpecification specified sort.
     * @param textComponent     component to sort.
     * @param symbol            specified symbol to check in sort.
     * @return list of sorted components.
     */
    public List<TextComponent> query(final SortSpecification sortSpecification,
                                     final TextComponent textComponent,
                                     final char symbol) {
        return sortSpecification.sort(textComponent, symbol);
    }
}
