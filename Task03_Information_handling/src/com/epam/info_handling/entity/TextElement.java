package com.epam.info_handling.entity;

public enum TextElement {
    TEXT(7),
    PARAGRAPH(6),
    SENTENCE(5),
    LEXEME(4),
    WORD(1),
    EXPRESSION(3),
    PUNCTUATION_MARK(1),
    SYMBOL(0);

    private int level;

    public int getLevel() {
        return level;
    }

    TextElement(int level) {
        this.level = level;
    }
}
