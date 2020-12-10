package com.biku.weather.exceptions;

public class WrongDataException extends NullPointerException {
    public WrongDataException(String message) {
        super(message);
    }
}
