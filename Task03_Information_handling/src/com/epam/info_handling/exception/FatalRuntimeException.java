package com.epam.info_handling.exception;

public class FatalRuntimeException extends RuntimeException {
    public FatalRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FatalRuntimeException(String message) {
        super(message);
    }
}
