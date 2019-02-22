package com.epam.objects.exception;

/**
 * Extended class exception to avoid situation when file path wrote incorrect.
 */
public class MissingFilePathException extends Exception {
    /**
     * Set a message for logging exception.
     * @param message exception message.
     */
    public MissingFilePathException(final String message) {
        super(message);
    }
}
