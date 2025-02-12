package ru.home.hibernate.exception;

public class UnauthorizedErrorException extends RuntimeException {
    public UnauthorizedErrorException(String message) {
        super(message);
    }
}
