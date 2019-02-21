package com.epam.objects.exception;

/**
 * Exception throws when got data in not enough.
 */
public class InvalidDataAmountException extends Exception {

    /**
     * Default constructor.
     */
    public InvalidDataAmountException() {
    }

    /**
     * Constructor - use when need to save a exception message.
     * @param message exception message.
     */
    public InvalidDataAmountException(final String message) {
        super(message);
    }
}
