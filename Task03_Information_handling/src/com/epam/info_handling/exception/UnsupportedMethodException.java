package com.epam.info_handling.exception;

/**
 * Extended class to avoid situation when method is not support.
 */
public class UnsupportedMethodException extends Exception {
    /**
     * Constructor.
     */
    public UnsupportedMethodException() {
    }

    /**
     * Set a message for logging exception.
     *
     * @param message exception message.
     */
    public UnsupportedMethodException(final String message) {
        super(message);
    }
}
