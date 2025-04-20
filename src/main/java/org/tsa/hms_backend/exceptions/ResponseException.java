package org.tsa.hms_backend.exceptions;

import org.springframework.http.HttpStatus;

public abstract class ResponseException extends RuntimeException {
    public ResponseException(String message) {
        super(message);
    }

    public ResponseException(String message, Throwable cause) {}

    public abstract HttpStatus getStatus();
}
