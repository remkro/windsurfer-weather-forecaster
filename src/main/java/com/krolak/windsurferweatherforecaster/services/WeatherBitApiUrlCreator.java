package com.krolak.windsurferweatherforecaster.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WeatherBitApiUrlCreator {
    @Value("${apiKey}")
    private String apiKey;
    @Value("${apiUrl}")
    private String apiUrl;

    public String create(String location) {
        return apiUrl + location + "&key=" + apiKey;
    }
}
