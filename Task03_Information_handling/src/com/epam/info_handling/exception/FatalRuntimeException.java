package com.epam.info_handling.exception;

/**
 * Extended class to show a fatal error in program and stops this one.
 */
public class FatalRuntimeException extends RuntimeException {
    /**
     * Constructor.
     */
    public FatalRuntimeException() {
    }

    /**
     * Constructor - creates exception with error message and cause of that
     * exception.
     *
     * @param message exception message.
     * @param cause   cause of exception.
     */
    public FatalRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor - creates exception with error message.
     *
     * @param message exception message.
     */
    public FatalRuntimeException(final String message) {
        super(message);
    }
}
