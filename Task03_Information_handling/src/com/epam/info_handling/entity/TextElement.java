package com.epam.info_handling.entity;

/**
 * Enum class that shows text levels.
 */
public enum TextElement {
    /**
     * Level text.
     */
    TEXT(7),
    /**
     * Level paragraph.
     */
    PARAGRAPH(6),
    /**
     * Level sentence.
     */
    SENTENCE(5),
    /**
     * Level lexeme.
     */
    LEXEME(4),
    /**
     * Level word.
     */
    WORD(1),
    /**
     * Level expression.
     */
    EXPRESSION(3),
    /**
     * Level punctuation mark.
     */
    PUNCTUATION_MARK(1),
    /**
     * Level symbol.
     */
    SYMBOL(0);

    /**
     * Field stores level.
     */
    private int level;

    /**
     * @return text level in int.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Constructor - initialize text level.
     *
     * @param textLevel text level in int.
     */
    TextElement(int textLevel) {
        this.level = textLevel;
    }
}
