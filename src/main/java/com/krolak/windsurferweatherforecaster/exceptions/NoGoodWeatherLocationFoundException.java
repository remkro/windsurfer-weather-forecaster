package com.krolak.windsurferweatherforecaster.exceptions;

public class NoGoodWeatherLocationFoundException extends RuntimeException {
    public NoGoodWeatherLocationFoundException(String message) {
        super(message);
    }
}
