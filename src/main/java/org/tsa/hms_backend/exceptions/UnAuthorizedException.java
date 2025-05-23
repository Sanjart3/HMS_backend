package org.tsa.hms_backend.exceptions;

import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends ResponseException{
    public UnAuthorizedException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
