package com.epam.info_handling.entity;

import com.epam.info_handling.exception.InvalidIndexException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextComponentImpl implements TextComponent {
    private static final Logger LOGGER
            = LogManager.getLogger(TextComponentImpl.class);

    private List<TextComponent> components = new ArrayList<>();

    private TextElement textElement;

    public TextComponentImpl(final TextElement element) {
        this.textElement = element;
    }

    public void add(final TextComponent... textComponents) {
        this.components.addAll(Arrays.asList(textComponents));
    }

    public void add(final TextComponent component) {
        components.add(component);
    }

    public void remove(final TextComponent component) {
        components.remove(component);
    }

    public void remove(final TextComponent... textComponents) {
        this.components.removeAll(Arrays.asList(textComponents));
    }

    public TextComponent getChild(final int index)
            throws InvalidIndexException {
        if (index < 0 || index >= components.size()) {
            throw new InvalidIndexException("Index out of bound.");
        }
        return components.get(index);
    }

    public TextElement getTextElement() {
        return textElement;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (TextComponent component : components) {
            builder.append(component.toString());
        }
        switch (textElement) {
            case LEXEME:
                builder.append(" ");
                break;
            case PARAGRAPH:
                builder.append("\n   ");
                break;
            case TEXT:
                builder = builder.reverse().append("   ").reverse();
            default:
        }
        return builder.toString();
    }
}
