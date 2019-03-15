package com.epam.info_handling.constant;

public enum TextTreeLevel {
    WHOLE_TEXT(0),
    PARAGRAPH(1),
    SENTENCE(2),
    LEXEM(3),
    WORD(4),
    EXPRESSION(5),
    PUNCTUATION_MARK(6),
    SYMBOL(7);

    int textLevel;

    TextTreeLevel(final int level) {
        this.textLevel = level;
    }

    public int getLevel() {
        return textLevel;
    }
}
