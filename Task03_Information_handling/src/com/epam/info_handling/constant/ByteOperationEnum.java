package com.epam.info_handling.constant;

/**
 * Class stores byte operations and it's priorities.
 */
public enum ByteOperationEnum {
    /**
     * Operation OR and priority.
     */
    OR(ByteOperation.OR, 1),
    /**
     * Operation XOR and priority.
     */
    XOR(ByteOperation.XOR, 2),
    /**
     * Operation AND and priority.
     */
    AND(ByteOperation.AND, 3),
    /**
     * Operation RIGHT SHIFT with fill nulls and priority.
     */
    RIGHT_SHIFT_NULL_FILL(ByteOperation.RIGHT_SHIFT_FILL_NULL, 4),
    /**
     * Operation RIGHT SHIFT and priority.
     */
    RIGHT_SHIFT(ByteOperation.RIGHT_SHIFT, 4),
    /**
     * Operation LEFT SHIFT and priority.
     */
    LEFT_SHIFT(ByteOperation.LEFT_SHIFT, 4),
    /**
     * Operation NOT and priority.
     */
    NOT(ByteOperation.NOT, 5),
    /**
     * Operation LEFT BRACE and priority.
     */
    LEFT_BRACE("(", 0);

    /**
     * String to stores operation.
     */
    private final String operation;
    /**
     * Field to stores operation priotrty.
     */
    private final int priority;

    /**
     * Constructor - initializing operation.
     *
     * @param operator          byte operator.
     * @param operationPriority operator priority.
     */
    ByteOperationEnum(final String operator, final int operationPriority) {
        this.operation = operator;
        this.priority = operationPriority;
    }

    /**
     * @return operation.
     */
    public String getOperation() {
        return operation;
    }

    /**
     * @return operation priority.
     */
    public int getPriority() {
        return priority;
    }
}
