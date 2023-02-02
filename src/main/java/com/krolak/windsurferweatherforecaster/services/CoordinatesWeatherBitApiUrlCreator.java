package com.krolak.windsurferweatherforecaster.services;

import com.krolak.windsurferweatherforecaster.interfaces.WeatherBitApiUrlCreator;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Map;

public class CoordinatesWeatherBitApiUrlCreator implements WeatherBitApiUrlCreator {
    @Value("${apiKey}")
    private String apiKey;
    @Value("${apiUrl}")
    private String apiUrl;

    public String create(Map.Entry<String, List<String>> locationsMap) {
        return apiUrl + "lat=" + locationsMap.getValue().get(0) + "&lon=" + locationsMap.getValue().get(1) + "&key=" + apiKey;
    }
}
