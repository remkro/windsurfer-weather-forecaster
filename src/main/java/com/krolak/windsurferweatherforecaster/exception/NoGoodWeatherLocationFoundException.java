package com.krolak.windsurferweatherforecaster.exception;

public class NoGoodWeatherLocationFoundException extends RuntimeException {
    public NoGoodWeatherLocationFoundException(String message) {
        super(message);
    }
}
