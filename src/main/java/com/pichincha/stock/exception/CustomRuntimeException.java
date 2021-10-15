package com.pichincha.stock.exception;

/**
 * @author mssalazarb
 * @version 1
 */
public class CustomRuntimeException extends RuntimeException {
    public CustomRuntimeException() {
    }

    public CustomRuntimeException(String message) {
        super(message);
    }

    public CustomRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
