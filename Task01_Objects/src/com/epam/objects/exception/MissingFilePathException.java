package com.epam.objects.exception;

public class MissingFilePathException extends Exception {
    public MissingFilePathException() {
    }

    public MissingFilePathException(final String message) {
        super(message);
    }
}
