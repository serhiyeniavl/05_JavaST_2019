package com.epam.threads.exception;

/**
 * Extended class to avoid situation when input file is empty.
 */
public class EmptyFileException extends Exception {
    /**
     * Set a message for logging exception.
     * @param message exception message.
     */
    public EmptyFileException(final String message) {
        super(message);
    }
}
