package com.krolak.windsurferweatherforecaster.services;

import com.krolak.windsurferweatherforecaster.dtos.GeneralWeatherResponseDto;
import com.krolak.windsurferweatherforecaster.dtos.GoodWeatherLocationDto;
import com.krolak.windsurferweatherforecaster.exceptions.NoLocationsProvidedException;
import com.krolak.windsurferweatherforecaster.interfaces.WeatherAnalysisService;
import com.krolak.windsurferweatherforecaster.interfaces.WeatherBitApiUrlCreator;
import com.krolak.windsurferweatherforecaster.interfaces.WindsurfingLocationFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WindsurfingLocationFinderImpl implements WindsurfingLocationFinder {
    private final RestTemplate restTemplate;
    private final WeatherAnalysisService weatherAnalysisService;
    private final WeatherBitApiUrlCreator weatherBitApiUrlCreator;
    @Value("#{${locations}}")
    private Map<String, List<String>> locations;

    public GoodWeatherLocationDto findBestWeatherLocation(LocalDate date) {
        List<GeneralWeatherResponseDto> responses = callWeatherBitApiToCheckWeatherForAllLocations();
        return weatherAnalysisService.filterLocations(responses, date);
    }

    private List<GeneralWeatherResponseDto> callWeatherBitApiToCheckWeatherForAllLocations() {
        if (locations.size() == 0)
            throw new NoLocationsProvidedException("There are no locations to check!");

        String resource;
        GeneralWeatherResponseDto response;
        List<GeneralWeatherResponseDto> responses = new ArrayList<>();

        for (Map.Entry<String, List<String>> entrySet : locations.entrySet()) {
            resource = weatherBitApiUrlCreator.create(entrySet.getKey(), entrySet.getValue());
            response = restTemplate.getForObject(resource, GeneralWeatherResponseDto.class);
            if (response == null)
                continue;
            responses.add(response);
        }

        return responses;
    }
}
