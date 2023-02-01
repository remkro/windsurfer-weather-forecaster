package com.krolak.windsurferweatherforecaster.services;

import com.krolak.windsurferweatherforecaster.dtos.WeatherResponseDto;
import com.krolak.windsurferweatherforecaster.exceptions.NoLocationsProvidedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherForecastService {
    private RestTemplate restTemplate = new RestTemplate();
    @Value("${apiKey}")
    private String apiKey;
    @Value("${apiUrl}")
    private String apiUrl;
    @Value("#{'${listOfCities}'.split(',')}")
    private List<String> locations;

    public String performForecast() {
        List<WeatherResponseDto> responses = callWeatherBitApiToCheckWeatherForAllLocations();
        return "good weather";
    }

    private List<WeatherResponseDto> callWeatherBitApiToCheckWeatherForAllLocations() {
        if (locations.size() == 0)
            throw new NoLocationsProvidedException("There are no locations to check!");

        String resource;
        WeatherResponseDto response;
        List<WeatherResponseDto> responses = new ArrayList<>();

        for (String location : locations) {
            resource = apiUrl + location + "&key=" + apiKey;
            response = restTemplate.getForObject(resource, WeatherResponseDto.class);
            responses.add(response);
        }

        return responses;
    }
}
