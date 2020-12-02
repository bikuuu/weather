package com.biku.weather.exceptions;

public class BadRequestException extends NullPointerException {
    public BadRequestException(String message) {
        super(message);
    }
}
