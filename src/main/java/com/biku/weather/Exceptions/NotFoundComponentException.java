package com.biku.weather.Exceptions;

import javassist.NotFoundException;

public class NotFoundComponentException extends Throwable {
    public NotFoundComponentException(String message) {
        super(message);
    }
}
