package com.epam.info_handling.interpretator;

@FunctionalInterface
public interface ByteExpression {
    void interpret(Context context);
}
