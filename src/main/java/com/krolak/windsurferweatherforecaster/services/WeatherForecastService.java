package com.krolak.windsurferweatherforecaster.services;

import com.krolak.windsurferweatherforecaster.dtos.GeneralWeatherResponseDto;
import com.krolak.windsurferweatherforecaster.dtos.GoodWeatherLocationDto;
import com.krolak.windsurferweatherforecaster.exceptions.NoLocationsProvidedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherForecastService {
    private final RestTemplate restTemplate;
    private final WeatherAnalysisService weatherAnalysisService;
    private final WeatherBitApiUrlCreator weatherBitApiUrlCreator;
    @Value("#{'${listOfCities}'.split(',')}")
    private List<String> locations;


    public GoodWeatherLocationDto performForecast(LocalDate date) {
        List<GeneralWeatherResponseDto> responses = callWeatherBitApiToCheckWeatherForAllLocations();
        GoodWeatherLocationDto locationWithGoodWeather = weatherAnalysisService.findLocationWithGoodWeather(responses, date);
        return locationWithGoodWeather;
    }

    private List<GeneralWeatherResponseDto> callWeatherBitApiToCheckWeatherForAllLocations() {
        if (locations.size() == 0)
            throw new NoLocationsProvidedException("There are no locations to check!");

        String resource;
        GeneralWeatherResponseDto response;
        List<GeneralWeatherResponseDto> responses = new ArrayList<>();

        for (String location : locations) {
            resource = weatherBitApiUrlCreator.create(location);
            response = restTemplate.getForObject(resource, GeneralWeatherResponseDto.class);
            responses.add(response);
        }

        return responses;
    }
}
