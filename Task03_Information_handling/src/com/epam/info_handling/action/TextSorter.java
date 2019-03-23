package com.epam.info_handling.action;

import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.sort.SortSpecification;

import java.util.List;

public class TextSorter {

    public List<TextComponent> query(final SortSpecification sortSpecification,
                                     final TextComponent textComponent) {
        return sortSpecification.sort(textComponent);
    }

    public List<TextComponent> query(final SortSpecification sortSpecification,
                                     final TextComponent textComponent,
                                     final char symbol) {
        return sortSpecification.sort(textComponent, symbol);
    }
}
