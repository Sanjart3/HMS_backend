package org.tsa.hms_backend.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("Can't find any user with provided username(%s) and password".formatted(username));
    }
}
