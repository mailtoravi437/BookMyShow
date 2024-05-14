package org.example.bookmyshow.exceptions;

public class InvalidStateException extends RuntimeException {
    public InvalidStateException(String invalidStateException) {
        super("Invalid state");
    }
}
