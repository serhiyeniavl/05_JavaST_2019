package com.epam.info_handling.action;

import com.epam.info_handling.constant.ByteOperationEnum;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Class for converting byte expression into polish notation.
 */
public class PolishNotationAnalyzer {
    /**
     * Converted polish notation.
     */
    private StringBuilder polishNotation;
    /**
     * Deque for storing operations.
     */
    private Deque<ByteOperationEnum> operationsDeque = new ArrayDeque<>();

    /**
     * Constant for identify first figure position in ASCII table.
     */
    private static final int FIRST_FIGURE_IN_ASCII_TABLE_POS = 48;
    /**
     * Constant for identify last figure position in ASCII table.
     */
    private static final int LAST_FIGURE_IN_ASCII_TABLE_POS = 57;
    /**
     * Constant for identify two sign operator len.
     */
    private static final int TWO_SIGN_OPERATOR_LEN = 2;
    /**
     * Constant for identify three sign operator len.
     */
    private static final int THREE_SIGN_OPERATOR_LEN = 3;

    /**
     * Field stores length of expression.
     */
    private int expressionLen;


    /**
     * Method analyzing expression and creates polish notation. Through loop
     * is is calling private method of this class. See how to create polish
     * notation from expression.
     *
     * @param byteExpression expression for analyze.
     * @return created polish notation.
     */
    public String analyzeAndGet(final String byteExpression) {
        polishNotation = new StringBuilder();
        expressionLen = byteExpression.length();

        int carriage = 0;

        while (carriage < expressionLen) {
            if (isFigure(byteExpression.charAt(carriage))) {
                carriage = findFigure(carriage, byteExpression) + 1;
                continue;
            }
            if (!isBrace(byteExpression.charAt(carriage))) {
                for (ByteOperationEnum operation : ByteOperationEnum.values()) {
                    if (tryAppendToDeque(carriage, operation, byteExpression)
                            && operation == ByteOperationEnum
                            .RIGHT_SHIFT_NULL_FILL) {
                        carriage += 2;
                        break;
                    }
                }
            }
            ++carriage;
        }

        cleanOperationDeque();
        return polishNotation.toString().trim();
    }

    /**
     * Appends figure into result notation.
     *
     * @param figure figure to append.
     */
    private void appendFigureToNotation(final String figure) {
        polishNotation.append(figure);
        polishNotation.append(" ");
    }

    /**
     * Appends operator into result notation.
     *
     * @param operation operator to append.
     */
    private void appendOperatorToNotation(final ByteOperationEnum operation) {
        polishNotation.append(operation.getOperation());
        polishNotation.append(" ");
    }

    /**
     * Appends the rest operators into result notation.
     */
    private void appendOperatorToNotation() {
        polishNotation.append(operationsDeque.pollLast().getOperation());
        polishNotation.append(" ");
    }

    /**
     * Tries append operator into notation. If priority that operator lower than
     * in queue it appends in.
     *
     * @param operation operation to try append.
     */
    private void tryAppendToNotation(final ByteOperationEnum operation) {
        if (!operationsDeque.isEmpty()
                && operationsDeque.peekLast().getPriority()
                >= operation.getPriority()) {
            appendOperatorToNotation();
        }
    }

    /**
     * Tries append into deque operator. If in can carriage moves forward.
     *
     * @param currentPos     current carriage in analyzer position.
     * @param operation      operation to try append.
     * @param byteExpression expression where operator from.
     * @return true if operator was appended, otherwise false.
     */
    private boolean tryAppendToDeque(final int currentPos,
                                     final ByteOperationEnum operation,
                                     final String byteExpression) {
        if ((handleSingleOperator(byteExpression.charAt(currentPos), operation))
                || (currentPos + THREE_SIGN_OPERATOR_LEN < expressionLen
                && handleOperator(byteExpression.substring(
                currentPos, (
                        currentPos + THREE_SIGN_OPERATOR_LEN)),
                operation))
                || ((currentPos + TWO_SIGN_OPERATOR_LEN < expressionLen)
                && handleOperator(
                byteExpression.substring(
                        currentPos, (currentPos + TWO_SIGN_OPERATOR_LEN)),
                operation))) {
            operationsDeque.add(operation);
            return true;
        }
        return false;
    }

    /**
     * Checks if current sign is brace. If it is left brace - add to queue.
     * If it is right brace - delete all operator to left brace from queue and
     * move in notation.
     *
     * @param operator operator to check.
     * @return true if it brace.
     */
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

    /**
     * Checks if symbol is a figure.
     *
     * @param symbol symbol to check.
     * @return true if symbol is figure.
     */
    private boolean isFigure(final char symbol) {
        return symbol >= FIRST_FIGURE_IN_ASCII_TABLE_POS
                && symbol
                <= LAST_FIGURE_IN_ASCII_TABLE_POS;
    }

    /**
     * Finds figure in string. While method finds figure in string it moves
     * carriage forward.
     *
     * @param currentPos carriage position in string.
     * @param expression expression to check.
     * @return new carriage position.
     */
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

    /**
     * Gets operator and checks in in coincidence with operator in expression.
     *
     * @param operator      operator in expression.
     * @param byteOperation operator to check.
     * @return true if operator was handled.
     */
    private boolean handleOperator(final String operator,
                                   final ByteOperationEnum byteOperation) {
        if (operator.equals(byteOperation.getOperation())) {
            tryAppendToNotation(byteOperation);
            return true;
        }
        return false;
    }

    /**
     * Gets one sign operator and checks in in coincidence with operator in
     * expression.
     *
     * @param operator  operator in expression.
     * @param operation operator to check.
     * @return true if operator was handled.
     */
    private boolean handleSingleOperator(final char operator,
                                         final ByteOperationEnum operation) {
        if (operation.getOperation().length() == 1
                && operator == operation.getOperation().charAt(0)) {
            tryAppendToNotation(operation);
            return true;
        }
        return false;
    }

    /**
     * Cleans the rest operator in deque and appends those into result notation.
     */
    private void cleanOperationDeque() {
        while (!operationsDeque.isEmpty()) {
            appendOperatorToNotation(operationsDeque.pollLast());
        }
    }
}
