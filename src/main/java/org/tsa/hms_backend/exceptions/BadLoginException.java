package org.tsa.hms_backend.exceptions;

public class BadLoginException extends RuntimeException {
    public BadLoginException() {
        super("Bad login credentials");
    }
}
