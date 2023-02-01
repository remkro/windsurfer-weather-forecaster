package com.krolak.windsurferweatherforecaster.services;

import com.krolak.windsurferweatherforecaster.dtos.WeatherResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherForecastService {
    private RestTemplate restTemplate = new RestTemplate();
    @Value("${apiKey}")
    private String apiKey;
    @Value("${apiUrl}")
    private String apiUrl;

    public String askForWeather() {
        String resource = apiUrl + "Jastarnia" + "&key=" + apiKey;
        WeatherResponseDto response = restTemplate.getForObject(resource, WeatherResponseDto.class);
        return response.toString();
    }
}
