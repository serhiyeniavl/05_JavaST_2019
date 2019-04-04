package com.epam.info_handling.exception;

/**
 * Extended class to avoid situation when index is invalid.
 */
public class InvalidIndexException extends Exception {
    /**
     * Constructor.
     */
    public InvalidIndexException() {
    }

    /**
     * Set a message for logging exception.
     *
     * @param message exception message.
     */
    public InvalidIndexException(final String message) {
        super(message);
    }
}
