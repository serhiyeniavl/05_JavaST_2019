package com.epam.info_handling.sort;

import com.epam.info_handling.entity.TextComponent;

import java.util.List;

public interface SortSpecification {
    List<TextComponent> sort(TextComponent components);
    List<TextComponent> sort(TextComponent components, char symbol);
}
