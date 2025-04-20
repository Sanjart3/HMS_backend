package org.tsa.hms_backend.exceptions;

import org.springframework.http.HttpStatus;

public class BadLoginException extends ResponseException {
    public BadLoginException() {
        super("Bad login credentials");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
