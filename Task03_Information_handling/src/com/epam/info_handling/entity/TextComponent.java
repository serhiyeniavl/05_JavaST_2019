package com.epam.info_handling.entity;

import com.epam.info_handling.exception.InvalidIndexException;
import com.epam.info_handling.exception.UnsupportedMethodException;

public interface TextComponent {
    void add(TextComponent component) throws UnsupportedMethodException;

    void add(TextComponent... components) throws UnsupportedMethodException;

    void remove(TextComponent component) throws UnsupportedMethodException;

    void remove(TextComponent... components) throws UnsupportedMethodException;

    TextComponent getChild(int index) throws InvalidIndexException,
            UnsupportedMethodException;

    String acquireWholeText() throws UnsupportedMethodException;

    String getData();

    String getName();
}
