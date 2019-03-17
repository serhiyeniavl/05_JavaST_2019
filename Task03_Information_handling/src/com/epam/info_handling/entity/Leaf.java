package com.epam.info_handling.entity;

import com.epam.info_handling.exception.UnsupportedMethodException;

public class Leaf implements TextComponent {
    private static final String ERROR_MSG
            = "Method is not supported in current class.";

    private String name;
    private String data;

    public Leaf(final String componentName, final String componentData) {
        this.name = componentName;
        this.data = componentData;
    }

    @Override
    public void add(final TextComponent component)
            throws UnsupportedMethodException {
        throw new UnsupportedMethodException(ERROR_MSG);
    }

    @Override
    public void add(final TextComponent... components)
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
    public TextComponent getChild(final int index)
            throws UnsupportedMethodException {
        throw new UnsupportedMethodException(ERROR_MSG);
    }

    @Override
    public String acquireWholeText()
            throws UnsupportedMethodException {
        throw new UnsupportedMethodException(ERROR_MSG);
    }

    @Override
    public String getData() {
        return data;
    }

    @Override
    public String getName() {
        return name;
    }
}
