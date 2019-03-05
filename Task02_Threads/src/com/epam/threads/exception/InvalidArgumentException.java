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

    /**
     * Constructor - creates exception with error message and cause of that
     * exception.
     *
     * @param message exception message.
     * @param cause   cause of exception.
     */
    public InvalidArgumentException(final String message,
                                    final Throwable cause) {
        super(message, cause);
    }
}
