package com.epam.info_handling.sort;

import com.epam.info_handling.action.TextCollector;
import com.epam.info_handling.entity.TextComponent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LexemeSymbolSortSpecification implements SortSpecification {
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

    @Override
    public List<TextComponent> sort(final TextComponent components) {
        return new ArrayList<>();
    }
}
