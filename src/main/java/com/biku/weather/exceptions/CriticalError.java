package com.biku.weather.exceptions;

public class CriticalError extends NullPointerException {
    public CriticalError(String message) {
        super(message);
    }
}
