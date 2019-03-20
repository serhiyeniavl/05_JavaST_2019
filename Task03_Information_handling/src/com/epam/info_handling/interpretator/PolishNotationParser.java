package com.epam.info_handling.interpretator;

import com.epam.info_handling.constant.ByteOperation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PolishNotationParser {

    public List<ByteExpression> parse(final String polishNotation) {
        List<ByteExpression> expression = new ArrayList<>();
        Arrays.asList(polishNotation.split("\\p{Blank}+")).forEach(token -> {
            switch (token) {
                case ByteOperation.OR:
                    expression.add(c -> c.push(c.poll() | c.poll()));
                    break;
                case ByteOperation.NOT:
                    expression.add(c -> c.push(~c.poll()));
                    break;
                case ByteOperation.XOR:
                    expression.add(c -> c.push(c.poll() ^ c.poll()));
                    break;
                case ByteOperation.AND:
                    expression.add(c -> c.push(c.poll() & c.poll()));
                    break;
                case ByteOperation.LEFT_SHIFT:
                    expression.add(c -> {
                        final int rightVal = c.poll();
                        final int leftVal = c.poll();
                        c.push(leftVal << rightVal);
                    });
                    break;
                case ByteOperation.RIGHT_SHIFT:
                    expression.add(c -> {
                        final int rightVal = c.poll();
                        final int leftVal = c.poll();
                        c.push(leftVal >> rightVal);
                    });
                    break;
                case ByteOperation.RIGHT_SHIFT_FILL_NULL:
                    expression.add(c -> {
                        final int rightVal = c.poll();
                        final int leftVal = c.poll();
                        c.push(leftVal >>> rightVal);
                    });
                    break;
                default:
                    expression.add(c -> c.push(Integer.parseInt(token)));
            }

        });
        return expression;
    }
}
