package com.epam.info_handling.sort;

import com.epam.info_handling.action.TextCollector;
import com.epam.info_handling.entity.TextComponent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class for sorting lexeme which has more specified symbols.
 */
public class LexemeSymbolSortSpecification implements SortSpecification {
    /**
     * Method sorts lexemes by defined symbol count in each one.
     *
     * @param components text components to sort.
     * @param symbol     symbol to sort.
     * @return sorted list of lexemes.
     */
    @Override
    public List<TextComponent> sort(final TextComponent components,
                                    final char symbol) {
        TextCollector textCollector = new TextCollector();
        List<TextComponent> lexemes = textCollector.acquireLexemes(components);
        lexemes.sort(Comparator
                .comparingInt(l -> l.toString().length() - l.toString()
                        .replace(Character.toString(symbol), "")
                        .length())
                .reversed()
                .thenComparing(Object::toString));
        return lexemes;
    }

    /**
     * @param components text components to sort.
     * @return empty list.
     */
    @Override
    public List<TextComponent> sort(final TextComponent components) {
        return new ArrayList<>();
    }
}
