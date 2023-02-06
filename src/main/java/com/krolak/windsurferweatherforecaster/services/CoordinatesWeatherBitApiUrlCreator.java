package com.krolak.windsurferweatherforecaster.services;

import com.krolak.windsurferweatherforecaster.interfaces.WeatherBitApiUrlCreator;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

//do katalogu utils zamiast services i wtedy juz bez interfejsu
public class CoordinatesWeatherBitApiUrlCreator implements WeatherBitApiUrlCreator {
    //jako jeden z nielicznych wykorzystujesz plik z propertisami wiec brawo, wszystkie zmienne wyciagnalbym do klasy EnvHelper i serwisy niech sobie biora stamtad getterami
    @Value("${apiKey}")
    private String apiKey;
    @Value("${apiUrl}")
    private String apiUrl;

    public String create(String city, List<String> coordinates) {
        return apiUrl + "lat=" + coordinates.get(0) + "&lon=" + coordinates.get(1) + "&key=" + apiKey;
    }
}
