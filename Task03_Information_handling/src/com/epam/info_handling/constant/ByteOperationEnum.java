package com.epam.info_handling.constant;

public enum ByteOperationEnum {
    OR(ByteOperation.OR, 1),
    XOR(ByteOperation.XOR, 2),
    AND(ByteOperation.AND, 3),
    RIGHT_SHIFT_NULL_FILL(ByteOperation.RIGHT_SHIFT_FILL_NULL, 4),
    RIGHT_SHIFT(ByteOperation.RIGHT_SHIFT, 4),
    LEFT_SHIFT(ByteOperation.LEFT_SHIFT, 4),
    NOT(ByteOperation.NOT, 5),
    LEFT_BRACE("(", 0);

    private final String operation;
    private final int priority;

    ByteOperationEnum(final String operator, final int operationPriority) {
        this.operation = operator;
        this.priority = operationPriority;
    }

    public String getOperation() {
        return operation;
    }

    public int getPriority() {
        return priority;
    }
}
