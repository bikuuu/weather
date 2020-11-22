package com.biku.weather.exceptions;

public class NoDataException extends RuntimeException {

    public NoDataException(String message){
        super(message);
    }
}
