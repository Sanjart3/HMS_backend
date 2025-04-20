package org.tsa.hms_backend.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends ResponseException{
    private HttpStatus status;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }
}
