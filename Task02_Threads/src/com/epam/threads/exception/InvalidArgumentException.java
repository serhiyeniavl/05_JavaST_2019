package com.epam.threads.exception;

/**
 * Extended class to avoid situation when argument is invalid.
 */
public class InvalidArgumentException extends Exception {
    /**
     * Set a message for logging exception.
     *
     * @param message exception message.
     */
    public InvalidArgumentException(final String message) {
        super(message);
    }
}
