package com.epam.info_handling.entity;

import com.epam.info_handling.exception.UnsupportedMethodException;

import java.util.List;

public class Leaf implements TextComponent {
    private static final String ERROR_MSG
            = "Method is not supported in current class.";

    private char data;

    private TextElement textElement;

    public Leaf(final char componentData, final TextElement element) {
        this.textElement = element;
        this.data = componentData;
    }

    @Override
    public void add(final TextComponent component)
            throws UnsupportedMethodException {
        throw new UnsupportedMethodException(ERROR_MSG);
    }

    @Override
    public void add(final List<TextComponent> components)
            throws UnsupportedMethodException {
        throw new UnsupportedMethodException(ERROR_MSG);
    }

    @Override
    public void remove(final TextComponent component)
            throws UnsupportedMethodException {
        throw new UnsupportedMethodException(ERROR_MSG);
    }

    @Override
    public void remove(final TextComponent... components)
            throws UnsupportedMethodException {
        throw new UnsupportedMethodException(ERROR_MSG);
    }

    @Override
    public List<TextComponent> getComponents()
            throws UnsupportedMethodException {
        throw new UnsupportedMethodException(ERROR_MSG);
    }

    @Override
    public TextComponent getChild(final int index)
            throws UnsupportedMethodException {
        throw new UnsupportedMethodException(ERROR_MSG);
    }

    public String getData() {
        return Character.toString(data);
    }

    @Override
    public TextElement getTextElement() {
        return textElement;
    }

    @Override
    public String toString() {
        return Character.toString(data);
    }
}
