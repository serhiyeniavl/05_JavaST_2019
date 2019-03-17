package com.epam.info_handling.constant;

public enum ByteOperation {
    OR(ConstByteOperation.OR, 1),
    XOR(ConstByteOperation.XOR, 2),
    AND(ConstByteOperation.AND, 3),
    RIGHT_SHIFT(ConstByteOperation.RIGHT_SHIFT, 4),
    RIGHT_SHIFT_NULL_FILL(ConstByteOperation.RIGHT_SHIFT_FILL_NULL, 4),
    LEFT_SHIFT(ConstByteOperation.LEFT_SHIFT, 4),
    NOT(ConstByteOperation.NOT, 5),
    LEFT_BRACE("(", 0);

    private final String operation;
    private final int priority;

    ByteOperation(final String operator, final int operationPriority) {
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
