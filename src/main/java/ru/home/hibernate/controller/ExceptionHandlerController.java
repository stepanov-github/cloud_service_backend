package ru.home.hibernate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.home.hibernate.dto.response.ErrorResponse;
import ru.home.hibernate.exception.BadRequestException;
import ru.home.hibernate.exception.InternalServerErrorException;
import ru.home.hibernate.exception.UnauthorizedErrorException;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorResponse badRequestException(BadRequestException exception) {
        return new ErrorResponse(exception.getMessage(), 400);
    }
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedErrorException.class)
    public ErrorResponse unauthorizedErrorException(UnauthorizedErrorException exception) {
        return new ErrorResponse(exception.getMessage(), 401);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerErrorException.class)
    public ErrorResponse internalServerErrorException(InternalServerErrorException exception) {
        return new ErrorResponse(exception.getMessage(), 500);
    }
}
