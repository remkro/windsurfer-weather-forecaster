package com.krolak.windsurferweatherforecaster.services;

import com.krolak.windsurferweatherforecaster.interfaces.WeatherBitApiUrlCreator;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class CoordinatesWeatherBitApiUrlCreator implements WeatherBitApiUrlCreator {
    @Value("${apiKey}")
    private String apiKey;
    @Value("${apiUrl}")
    private String apiUrl;

    public String create(String city, List<String> coordinates) {
        return apiUrl + "lat=" + coordinates.get(0) + "&lon=" + coordinates.get(1) + "&key=" + apiKey;
    }
}
