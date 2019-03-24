package com.epam.info_handling.entity;

import com.epam.info_handling.exception.UnsupportedMethodException;

import java.util.List;

/**
 * Last class in composite chain. Stores symbol of the text.
 */
public class Leaf implements TextComponent {
    /**
     * Error message when call method which doesn't support.
     */
    private static final String ERROR_MSG
            = "Method is not supported in current class.";

    /**
     * Symbol.
     */
    private char data;

    /**
     * Current text element.
     */
    private TextElement textElement;

    /**
     * Constructor - initialize the symbol and element.
     * @param componentData symbol.
     * @param element text element.
     */
    public Leaf(final char componentData, final TextElement element) {
        this.textElement = element;
        this.data = componentData;
    }

    /**
     * Method does not support by this class.
     * @param component component to add.
     * @throws UnsupportedMethodException when call this method.
     */
    @Override
    public void add(final TextComponent component)
            throws UnsupportedMethodException {
        throw new UnsupportedMethodException(ERROR_MSG);
    }

    /**
     * Method does not support by this class.
     * @param components components to add.
     * @throws UnsupportedMethodException when call this method.
     */
    @Override
    public void add(final List<TextComponent> components)
            throws UnsupportedMethodException {
        throw new UnsupportedMethodException(ERROR_MSG);
    }

    /**
     * Method does not support by this class.
     * @param component component to remove.
     * @throws UnsupportedMethodException when call this method.
     */
    @Override
    public void remove(final TextComponent component)
            throws UnsupportedMethodException {
        throw new UnsupportedMethodException(ERROR_MSG);
    }

    /**
     * Method does not support by this class.
     * @param components component to remove.
     * @throws UnsupportedMethodException when call this method.
     */
    @Override
    public void remove(final List<TextComponent> components)
            throws UnsupportedMethodException {
        throw new UnsupportedMethodException(ERROR_MSG);
    }

    /**
     * Method does not support by this class.
     * @return components from composite.
     * @throws UnsupportedMethodException when call this method.
     */
    @Override
    public List<TextComponent> getComponents()
            throws UnsupportedMethodException {
        throw new UnsupportedMethodException(ERROR_MSG);
    }

    /**
     * Method does not support by this class.
     * @param index index of component.
     * @return component from composite.
     * @throws UnsupportedMethodException when call this method.
     */
    @Override
    public TextComponent getChild(final int index)
            throws UnsupportedMethodException {
        throw new UnsupportedMethodException(ERROR_MSG);
    }

    /**
     * @return symbol that leaf stores.
     */
    public String getData() {
        return Character.toString(data);
    }

    /**
     * @return text element that leaf stores.
     */
    @Override
    public TextElement getTextElement() {
        return textElement;
    }

    /**
     * @return symbol that leaf stores.
     */
    @Override
    public String toString() {
        return Character.toString(data);
    }
}
