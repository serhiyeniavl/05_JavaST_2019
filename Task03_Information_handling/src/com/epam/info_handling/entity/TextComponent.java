package com.epam.info_handling.entity;

import com.epam.info_handling.constant.TextLevel;
import com.epam.info_handling.exception.InvalidIndexException;
import com.epam.info_handling.exception.UnsupportedMethodException;

import java.util.List;

public interface TextComponent {
    void add(TextComponent component) throws UnsupportedMethodException;

    void add(TextComponent... components) throws UnsupportedMethodException;

    void remove(TextComponent component) throws UnsupportedMethodException;

    void remove(TextComponent... components) throws UnsupportedMethodException;

    TextComponent getChild(int index) throws InvalidIndexException,
            UnsupportedMethodException;

    List<String> acquireComponent(TextLevel level)
            throws UnsupportedMethodException;

    String getData();

    String getName();
}
