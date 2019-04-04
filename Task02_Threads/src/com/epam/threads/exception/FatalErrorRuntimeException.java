package com.epam.threads.exception;

/**
 * Extended class to show a fatal error in program and stops this one.
 */
public class FatalErrorRuntimeException extends RuntimeException {
    /**
     * Constructor.
     */
    public FatalErrorRuntimeException() {
    }

    /**
     * Constructor - creates exception with error message.
     *
     * @param message exception message.
     */
    public FatalErrorRuntimeException(final String message) {
        super(message);
    }

    /**
     * Constructor - creates exception with error message and cause of that
     * exception.
     *
     * @param message exception message.
     * @param cause   cause of exception.
     */
    public FatalErrorRuntimeException(final String message,
                                      final Throwable cause) {
        super(message, cause);
    }
}
