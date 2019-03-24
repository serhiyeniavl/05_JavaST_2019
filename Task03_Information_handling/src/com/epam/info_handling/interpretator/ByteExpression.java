package com.epam.info_handling.interpretator;

/**
 * Functional interface for pattern Interpretator.
 */
@FunctionalInterface
public interface ByteExpression {
    /**
     * Interprets expression depends on content.
     *
     * @param context to interpret.
     */
    void interpret(Context context);
}
