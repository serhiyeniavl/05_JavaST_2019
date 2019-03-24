package com.epam.info_handling.sort;

import com.epam.info_handling.entity.TextComponent;

import java.util.List;

/**
 * Interface for define hierarchy of sorts text components.
 */
public interface SortSpecification {
    /**
     * Sorts text components.
     *
     * @param components text components to sort.
     * @return list of sorted components.
     */
    List<TextComponent> sort(TextComponent components);

    /**
     * Sorts text components.
     *
     * @param components text components to sort.
     * @param symbol     symbol to define.
     * @return list of sorted components.
     */
    List<TextComponent> sort(TextComponent components, char symbol);
}
