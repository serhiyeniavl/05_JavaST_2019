package com.epam.info_handling.entity;

import com.epam.info_handling.exception.InvalidIndexException;
import com.epam.info_handling.exception.UnsupportedMethodException;

import java.util.List;

/**
 * Interface for pattern Composite. Defines specified method for successors.
 */
public interface TextComponent {
    /**
     * Method adds component into composite.
     * @param component component to add.
     * @throws UnsupportedMethodException when operation does support. In our
     * case - in leaf.
     */
    void add(TextComponent component) throws UnsupportedMethodException;

    /**
     * Method adds list of components into composite.
     * @param components components to add.
     * @throws UnsupportedMethodException when operation does support. In our
     * case - in leaf.
     */
    void add(List<TextComponent> components)
            throws UnsupportedMethodException;

    /**
     * Method removes component from composite.
     * @param component component to remove.
     * @throws UnsupportedMethodException when operation does support. In our
     * case - in leaf.
     */
    void remove(TextComponent component) throws UnsupportedMethodException;

    /**
     * Method removes components from composite.
     * @param components component to remove.
     * @throws UnsupportedMethodException when operation does support. In our
     * case - in leaf.
     */
    void remove(List<TextComponent> components)
            throws UnsupportedMethodException;

    /**
     * @return components from composite.
     * @throws UnsupportedMethodException when operation does support. In our
     * case - in leaf.
     */
    List<TextComponent> getComponents()
            throws UnsupportedMethodException;

    /**
     * Return specified component from composite.
     * @param index index of component.
     * @return component from composite.
     * @throws InvalidIndexException when index is invalid or out of bound.
     * @throws UnsupportedMethodException when operation does support. In our
     * case - in leaf.
     */
    TextComponent getChild(int index) throws InvalidIndexException,
            UnsupportedMethodException;

    /**
     * @return text type of current component.
     */
    TextElement getTextElement();

    /**
     * @return string representation of composite.
     */
    String toString();
}
