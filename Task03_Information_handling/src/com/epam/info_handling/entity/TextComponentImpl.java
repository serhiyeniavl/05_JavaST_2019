package com.epam.info_handling.entity;

import com.epam.info_handling.action.PolishNotationAnalyzer;
import com.epam.info_handling.exception.InvalidIndexException;
import com.epam.info_handling.exception.UnsupportedMethodException;
import com.epam.info_handling.interpretator.ByteExpression;
import com.epam.info_handling.interpretator.ByteExpressionCalculator;
import com.epam.info_handling.interpretator.PolishNotationParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that stores whole text components. Implements all methods from
 * {@link TextComponent} interface.
 */
public class TextComponentImpl implements TextComponent {
    /**
     * Logger for define exception reason and logging it.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(TextComponent.class);

    /**
     * List of text components.
     */
    private List<TextComponent> components = new ArrayList<>();
    /**
     * Text element.
     */
    private TextElement textElement;

    /**
     * Copy constructor.
     * @param textComponent component to copy.
     */
    public TextComponentImpl(final TextComponent textComponent) {
        try {
            this.components = textComponent.getComponents();
            this.textElement = textComponent.getTextElement();
        } catch (UnsupportedMethodException e) {
            LOGGER.error("Error when try to copy text elements.", e);
        }
    }

    /**
     * Constructor - defined text element.
     * @param element element to add in components.
     */
    public TextComponentImpl(final TextElement element) {
        this.textElement = element;
    }

    /**
     * Add components in composite.
     * @param textComponents components to add.
     */
    public void add(final List<TextComponent> textComponents) {
        this.components.addAll(textComponents);
    }

    /**
     * Add component in composite.
     * @param component component to add.
     */
    public void add(final TextComponent component) {
        components.add(component);
    }

    /**
     * Removes component from composite.
     * @param component component to remove.
     */
    public void remove(final TextComponent component) {
        components.remove(component);
    }

    /**
     * Removes components from composite.
     * @param textComponents component to remove.
     */
    public void remove(final List<TextComponent> textComponents) {
        this.components.removeAll(textComponents);
    }

    /**
     * Return specified component from components.
     * @param index index of component.
     * @return component under index.
     * @throws InvalidIndexException when index is invalid.
     */
    public TextComponent getChild(final int index)
            throws InvalidIndexException {
        validate(index);
        return components.get(index);
    }

    /**
     * @return text components.
     */
    public List<TextComponent> getComponents() {
        return new ArrayList<>(components);
    }

    /**
     * @return current text element.
     */
    public TextElement getTextElement() {
        return textElement;
    }

    /**
     * @return string representation of text. Add whitespaces between lexemes,
     * sentences and paragraphs.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (TextComponent component : components) {
            builder.append(component.toString());
        }
        switch (textElement) {
            case EXPRESSION:
                String polishNotation = new PolishNotationAnalyzer()
                        .analyzeAndGet(builder.toString());
                List<ByteExpression> expressionList = new PolishNotationParser()
                        .parse(polishNotation);
                builder = new StringBuilder(
                        String.valueOf((int) new ByteExpressionCalculator()
                                .handleExpression(expressionList)));
                break;
            case LEXEME:
                builder.append(" ");
                break;
            case PARAGRAPH:
                builder.append("\n    ");
                break;
            case TEXT:
                builder = builder.reverse().append("    ").reverse();
            default:
        }
        return builder.toString();
    }

    private void validate(final int index) throws InvalidIndexException {
        if (index < 0 || index >= components.size()) {
            throw new InvalidIndexException("Index out of bound.");
        }
    }
}
