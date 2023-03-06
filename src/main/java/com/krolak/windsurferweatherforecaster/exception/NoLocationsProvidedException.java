package com.krolak.windsurferweatherforecaster.exception;

public class NoLocationsProvidedException extends RuntimeException {
    public NoLocationsProvidedException(String message) {
        super(message);
    }
}
