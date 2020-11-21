package com.biku.weather.Exceptions;

public class OverRangeException extends RuntimeException {
    public OverRangeException(String message){
        super(message);
    }
}
