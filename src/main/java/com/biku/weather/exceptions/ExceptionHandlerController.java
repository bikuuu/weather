package com.biku.weather.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(NoDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void noDataExceptionHandler(NoDataException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void constrainViolationException(ConstraintViolationException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(NotFoundComponentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void noFoundComponentExceptionHandler(NotFoundComponentException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(OverRangeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void overRangeExceptionHandler(OverRangeException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void badRequestException(BadRequestException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(WrongDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void wrongDataException(WrongDataException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(CriticalError.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    void criticalError(CriticalError exception) {
        log.error(exception.getMessage());
    }
}
