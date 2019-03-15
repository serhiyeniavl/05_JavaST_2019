package com.epam.info_handling.entity;

import com.epam.info_handling.exception.InvalidIndexException;

public interface TextComponent {
    void add(TextComponent component);

    void add(TextComponent... components);

    void remove(TextComponent component);

    void remove(TextComponent... components);

    TextComponent getChild(int index) throws InvalidIndexException;

    void acquireComponent(int level);

    String getData();
}
