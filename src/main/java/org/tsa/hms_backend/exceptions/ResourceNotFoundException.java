package org.tsa.hms_backend.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ResponseException{
    public ResourceNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
