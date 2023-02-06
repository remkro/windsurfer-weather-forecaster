package com.krolak.windsurferweatherforecaster.services;

import com.krolak.windsurferweatherforecaster.interfaces.WeatherBitApiUrlCreator;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

//do katalogu utils i bez interfejsu
public class CityWeatherBitApiUrlCreator implements WeatherBitApiUrlCreator {
    @Value("${apiKey}")
    private String apiKey;
    @Value("${apiUrl}")
    private String apiUrl;

    public String create(String city, List<String> coordinates) {
        return apiUrl + "city=" + city + "&key=" + apiKey;
    }
}
