package com.epam.info_handling.action;

import com.epam.info_handling.constant.ByteOperation;
import com.epam.info_handling.constant.ConstByteOperation;

import java.util.ArrayDeque;
import java.util.Deque;

public class PolishNotationAnalyzer {
    private StringBuilder polishNotation;
    Deque<ByteOperation> operationsDeque;


    public String analyzeAndGet(final String byteExpression) {
        polishNotation = new StringBuilder();
        operationsDeque = new ArrayDeque();
        final int expLen = byteExpression.length();
        final int firstFigureInASCIITablePos = 48;
        final int lastFigureInASCIITablePos = 57;
        int figureInitPos = -1;
        for (int i = 0; i < expLen; i++) {
            if (byteExpression.charAt(i) >= firstFigureInASCIITablePos
                    && byteExpression.charAt(i) <= lastFigureInASCIITablePos) {
                if (figureInitPos == -1) {
                    figureInitPos = i;
                }
                if (i == expLen - 1) {
                    appendFigure(byteExpression, figureInitPos, i + 1);
                    break;
                }
                continue;
            } else if (figureInitPos != -1) {
                appendFigure(byteExpression, figureInitPos, i);
                figureInitPos = -1;
            }
            if (byteExpression.charAt(i) == '(') {
                operationsDeque.add(ByteOperation.LEFT_BRACE);
            } else if (byteExpression.charAt(i) == ')') {
                while (operationsDeque.peekLast() != ByteOperation.LEFT_BRACE) {
                    polishNotation.append(operationsDeque.pollLast().getOperation());
                    polishNotation.append(" ");
                }
                operationsDeque.pollLast();
            } else if (byteExpression.charAt(i) == ConstByteOperation.AND.charAt(0)) {
                if (!operationsDeque.isEmpty() && operationsDeque.peekLast().getPriority() >= ByteOperation.AND.getPriority()) {
                    polishNotation.append(operationsDeque.pollLast().getOperation());
                    polishNotation.append(" ");
                }
                operationsDeque.add(ByteOperation.AND);
            } else if (byteExpression.charAt(i) == ConstByteOperation.OR.charAt(0)) {
                if (!operationsDeque.isEmpty() && operationsDeque.peekLast().getPriority() >= ByteOperation.OR.getPriority()) {
                    polishNotation.append(operationsDeque.pollLast().getOperation());
                    polishNotation.append(" ");
                }
                operationsDeque.add(ByteOperation.OR);
            } else if (byteExpression.charAt(i) == ConstByteOperation.XOR.charAt(0)) {
                if (!operationsDeque.isEmpty() && operationsDeque.peekLast().getPriority() >= ByteOperation.XOR.getPriority()) {
                    polishNotation.append(operationsDeque.pollLast().getOperation());
                    polishNotation.append(" ");
                }
                operationsDeque.add(ByteOperation.XOR);
            } else if (byteExpression.substring(i, i + 2).equals(ConstByteOperation.RIGHT_SHIFT)) {
                if (!operationsDeque.isEmpty() && operationsDeque.peekLast().getPriority() >= ByteOperation.RIGHT_SHIFT.getPriority()) {
                    polishNotation.append(operationsDeque.pollLast().getOperation());
                    polishNotation.append(" ");
                }
                operationsDeque.add(ByteOperation.RIGHT_SHIFT);
                ++i;
            } else if (byteExpression.charAt(i) == ConstByteOperation.NOT.charAt(0)) {
                if (!operationsDeque.isEmpty() && operationsDeque.peekLast().getPriority() >= ByteOperation.NOT.getPriority()) {
                    polishNotation.append(operationsDeque.pollLast().getOperation());
                    polishNotation.append(" ");
                }
                operationsDeque.add(ByteOperation.NOT);

            } else if (!operationsDeque.isEmpty() && byteExpression.substring(i, i + 3).equals(ConstByteOperation.RIGHT_SHIFT_FILL_NULL)) {
                if (operationsDeque.peekLast().getPriority() >= ByteOperation.RIGHT_SHIFT_NULL_FILL.getPriority()) {
                    polishNotation.append(operationsDeque.pollLast().getOperation());
                    polishNotation.append(" ");
                }
                operationsDeque.add(ByteOperation.RIGHT_SHIFT_NULL_FILL);
                i += 2;
            } else if (byteExpression.substring(i, i + 2).equals(ConstByteOperation.LEFT_SHIFT)) {
                if (!operationsDeque.isEmpty() && operationsDeque.peekLast().getPriority() >= ByteOperation.LEFT_SHIFT.getPriority()) {
                    polishNotation.append(operationsDeque.pollLast().getOperation());
                    polishNotation.append(" ");
                }
                operationsDeque.add(ByteOperation.LEFT_SHIFT);
                ++i;
            }
        }
        while (!operationsDeque.isEmpty()) {
            appendOperator(operationsDeque.pollLast());
        }
        return polishNotation.toString().trim();
    }

    private void appendFigure(final String byteExpression,
                              final int beg, final int end) {
        polishNotation.append(byteExpression.substring(beg, end));
        polishNotation.append(" ");
    }

    private void appendOperator(final ByteOperation operation) {
        polishNotation.append(operation.getOperation());
        polishNotation.append(" ");
    }

    private void appendOperatorToNotation() {
        polishNotation.append(operationsDeque.pollLast().getOperation());
        polishNotation.append(" ");
    }

    private boolean handleSingleOperator(final char operator,
                                         final ByteOperation byteOperation) {
        if (!operationsDeque.isEmpty()
                && operator == byteOperation.getOperation().charAt(0)
                && operationsDeque.peekLast().getPriority()
                >= byteOperation.getPriority()) {
            appendOperatorToNotation();
            return true;
        }
        return false;
    }
}
