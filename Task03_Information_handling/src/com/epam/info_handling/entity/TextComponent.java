package com.epam.info_handling.entity;

import com.epam.info_handling.exception.InvalidIndexException;
import com.epam.info_handling.exception.UnsupportedMethodException;

import java.util.List;

public interface TextComponent {
    void add(TextComponent component) throws UnsupportedMethodException;

    void add(List<TextComponent> components)
            throws UnsupportedMethodException;

    void remove(TextComponent component) throws UnsupportedMethodException;

    void remove(List<TextComponent> components)
            throws UnsupportedMethodException;

    List<TextComponent> getComponents()
            throws UnsupportedMethodException;

    TextComponent getChild(int index) throws InvalidIndexException,
            UnsupportedMethodException;

    TextElement getTextElement();

    String toString();
}
