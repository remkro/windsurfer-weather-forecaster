package com.krolak.windsurferweatherforecaster.exceptions;

public class NoLocationsProvidedException extends RuntimeException {
    public NoLocationsProvidedException(String message) {
        super(message);
    }
}
