package com.epam.threads.exception;

/**
 * Extended class to avoid situation when call of the method is not correct.
 */
public class IllegalCallException extends Exception {
    /**
     * Constructor.
     */
    public IllegalCallException() {
    }

    /**
     * Set a message for logging exception.
     *
     * @param message exception message.
     */
    public IllegalCallException(final String message) {
        super(message);
    }
}
