package com.epam.info_handling.interpretator;

import java.util.List;

public class ByteExpressionCalculator {
    private Context context = new Context();

    public double handleExpression(final List<ByteExpression> expression) {
        expression.forEach(exp -> exp.interpret(context));
        return context.poll();
    }
}
