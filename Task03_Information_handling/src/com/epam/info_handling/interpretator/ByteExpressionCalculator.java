package com.epam.info_handling.interpretator;

import java.util.List;

/**
 * Class for interpreting byte expressions.
 */
public class ByteExpressionCalculator {
    /**
     * Context.
     */
    private Context context = new Context();

    /**
     * Interprets expression depends on context.
     *
     * @param expression expression to interpret.
     * @return calculated expression.
     */
    public double handleExpression(final List<ByteExpression> expression) {
        expression.forEach(exp -> exp.interpret(context));
        return context.poll();
    }
}
