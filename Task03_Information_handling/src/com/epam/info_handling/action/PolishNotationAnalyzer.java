package com.epam.info_handling.action;

import com.epam.info_handling.constant.ByteOperation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PolishNotationAnalyzer {
    private StringBuilder polishNotation;
    private Deque<ByteOperation> operationsDeque;


    public String analyzeAndGet(final String byteExpression) {
        polishNotation = new StringBuilder();
        operationsDeque = new ArrayDeque();

        final int expLen = byteExpression.length();
        final int firstFigureInASCIITablePos = 48;
        final int lastFigureInASCIITablePos = 57;
        final int twoSignOperatorLen = 2;
        final int threeSignOperatorLen = 3;
        int figureInitPos = -1;

        boolean isSingleOperator;

        List<ByteOperation> singleByteOperations = new ArrayList<>();
        List<ByteOperation> longByteOperations = new ArrayList<>();
        for (ByteOperation operation : ByteOperation.values()) {
            if (operation == ByteOperation.LEFT_BRACE) {
                continue;
            }
            if (operation.getOperation().length() == 1) {
                singleByteOperations.add(operation);
            } else {
                longByteOperations.add(operation);
            }
        }

        for (int i = 0; i < expLen; i++) {
            isSingleOperator = false;
            if (byteExpression.charAt(i) >= firstFigureInASCIITablePos
                    //TODO: try to reduce this method as possible
                    && byteExpression.charAt(i) <= lastFigureInASCIITablePos) {
                if (figureInitPos == -1) {
                    figureInitPos = i;
                }
                if (i == expLen - 1) {
                    appendFigureToNotation(
                            byteExpression.substring(figureInitPos, i + 1));
                }
                continue;
            } else if (figureInitPos != -1) {
                appendFigureToNotation(byteExpression.substring(figureInitPos, i));
                figureInitPos = -1;
            }
            if (!isBrace(byteExpression.charAt(i))) {
                for (ByteOperation operation : singleByteOperations) {
                    if (handleSingleOperator(
                            byteExpression.charAt(i), operation)) {
                        operationsDeque.add(operation);
                        isSingleOperator = true;
                        break;
                    }
                }
                if (!isSingleOperator) {
                    for (ByteOperation operation : longByteOperations) {
                        if (handleOperator(
                                byteExpression.substring(
                                        i, (i + twoSignOperatorLen)), operation)
                                || handleOperator(
                                byteExpression.substring(
                                        i, (i + threeSignOperatorLen)),
                                operation)) {
                            operationsDeque.add(operation);
                        }
                    }
                }
            }
        }

        while (!operationsDeque.isEmpty()) {
            appendOperator(operationsDeque.pollLast());
        }

        return polishNotation.toString().trim();
    }

    private void appendFigureToNotation(final String byteExpression) {
        polishNotation.append(byteExpression);
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

    private boolean isBrace(final char operator) {
        if (operator == '(') {
            operationsDeque.add(ByteOperation.LEFT_BRACE);
        } else if (operator == ')') {
            while (operationsDeque.peekLast() != ByteOperation.LEFT_BRACE) {
                appendOperatorToNotation();
            }
            operationsDeque.pollLast();
        }
        return operator == '(' || operator == ')';
    }

    private boolean handleOperator(final String operator,
                                   final ByteOperation byteOperation) {
        if (operator.equals(byteOperation.getOperation())) {
            tryAppendToNotation(byteOperation);
            return true;
        }
        return false;
    }

    private boolean handleSingleOperator(final char operator,
                                         final ByteOperation byteOperation) {
        if (operator == byteOperation.getOperation().charAt(0)) {
            tryAppendToNotation(byteOperation);
            return true;
        }
        return false;
    }

    private void tryAppendToNotation(final ByteOperation operation) {
        if (!operationsDeque.isEmpty()
                && operationsDeque.peekLast().getPriority()
                >= operation.getPriority()) {
            appendOperatorToNotation();
        }
    }
}
