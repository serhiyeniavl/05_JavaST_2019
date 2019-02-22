package com.epam.objects.exception;

public class NullArgumentException extends Exception {
    public NullArgumentException() {
    }

    public NullArgumentException(String message) {
        super(message);
    }
}
