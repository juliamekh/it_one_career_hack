package com.itonehack.smartbudget.domain.exception;

/**
 * Custom exception to represent a Forbidden error.
 */
public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}
