package com.epam.info_handling.constant;

public enum TextLevel {
    WHOLE_TEXT(0),
    PARAGRAPH(1),
    SENTENCE(2),
    LEXEM(3),
    WORD(4),
    EXPRESSION(5),
    PUNCTUATION_MARK(6),
    SYMBOL(7);

    private int level;

    TextLevel(final int textLevel) {
        this.level = textLevel;
    }

    public int getValue() {
        return level;
    }
}
