package com.epam.info_handling.action;

import com.epam.info_handling.constant.ByteOperationEnum;

import java.util.ArrayDeque;
import java.util.Deque;

public class PolishNotationAnalyzer {
    private StringBuilder polishNotation;
    private Deque<ByteOperationEnum> operationsDeque;

    private static final int FIRST_FIGURE_IN_ASCII_TABLE_POS = 48;
    private static final int LAST_FIGURE_IN_ASCII_TABLE_POS = 57;
    private static final int TWO_SIGN_OPERATOR_LEN = 2;
    private static final int THREE_SIGN_OPERATOR_LEN = 3;

    private int expressionLen;


    public String analyzeAndGet(final String byteExpression) {
        polishNotation = new StringBuilder();
        operationsDeque = new ArrayDeque();
        expressionLen = byteExpression.length();

        int carriage = 0;

        while (carriage < expressionLen) {
            if (isFigure(byteExpression.charAt(carriage))) {
                carriage = findFigure(carriage, byteExpression) + 1;
                continue;
            }
            if (!isBrace(byteExpression.charAt(carriage))) {
                for (ByteOperationEnum operation : ByteOperationEnum.values()) {
                    if (tryAppendToDeque(carriage, operation, byteExpression)) {
                        break;
                    }
                }
            }
            ++carriage;
        }

        cleanOperationDeque();
        return polishNotation.toString().trim();
    }

    private void appendFigureToNotation(final String figure) {
        polishNotation.append(figure);
        polishNotation.append(" ");
    }

    private void appendOperatorToNotation(final ByteOperationEnum operation) {
        polishNotation.append(operation.getOperation());
        polishNotation.append(" ");
    }

    private void appendOperatorToNotation() {
        polishNotation.append(operationsDeque.pollLast().getOperation());
        polishNotation.append(" ");
    }


    private void tryAppendToNotation(final ByteOperationEnum operation) {
        if (!operationsDeque.isEmpty()
                && operationsDeque.peekLast().getPriority()
                >= operation.getPriority()) {
            appendOperatorToNotation();
        }
    }

    private boolean tryAppendToDeque(final int currentPos,
                                     final ByteOperationEnum operation,
                                     final String byteExpression) {
        if ((handleSingleOperator(byteExpression.charAt(currentPos), operation))
                || (currentPos + TWO_SIGN_OPERATOR_LEN < expressionLen
                && handleOperator(byteExpression.substring(
                currentPos, (
                        currentPos + TWO_SIGN_OPERATOR_LEN)),
                operation))
                || ((currentPos + THREE_SIGN_OPERATOR_LEN < expressionLen)
                && handleOperator(
                byteExpression.substring(
                        currentPos, (currentPos + THREE_SIGN_OPERATOR_LEN)),
                operation))) {
            operationsDeque.add(operation);
            return true;
        }
        return false;
    }

    private boolean isBrace(final char operator) {
        if (operator == '(') {
            operationsDeque.add(ByteOperationEnum.LEFT_BRACE);
        } else if (operator == ')') {
            while (operationsDeque.peekLast() != ByteOperationEnum.LEFT_BRACE) {
                appendOperatorToNotation();
            }
            operationsDeque.pollLast();
        }
        return operator == '(' || operator == ')';
    }

    private boolean isFigure(final char symbol) {
        return symbol >= FIRST_FIGURE_IN_ASCII_TABLE_POS
                && symbol
                <= LAST_FIGURE_IN_ASCII_TABLE_POS;
    }

    private int findFigure(final int currentPos, final String expression) {
        int carriage = currentPos;
        while (carriage + 1 != expressionLen
                && isFigure(expression.charAt(carriage + 1))) {
            carriage++;
        }
        if (currentPos == expressionLen - 1) {
            appendFigureToNotation(expression.substring(currentPos));
        } else {
            appendFigureToNotation(
                    expression.substring(currentPos, carriage + 1));
        }
        return carriage;
    }

    private boolean handleOperator(final String operator,
                                   final ByteOperationEnum byteOperation) {
        if (operator.equals(byteOperation.getOperation())) {
            tryAppendToNotation(byteOperation);
            return true;
        }
        return false;
    }

    private boolean handleSingleOperator(final char operator,
                                         final ByteOperationEnum operation) {
        if (operation.getOperation().length() == 1
                && operator == operation.getOperation().charAt(0)) {
            tryAppendToNotation(operation);
            return true;
        }
        return false;
    }

    private void cleanOperationDeque() {
        while (!operationsDeque.isEmpty()) {
            appendOperatorToNotation(operationsDeque.pollLast());
        }
    }
}
