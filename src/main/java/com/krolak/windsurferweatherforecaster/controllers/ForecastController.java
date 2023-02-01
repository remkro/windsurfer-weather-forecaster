package com.krolak.windsurferweatherforecaster.controllers;

import com.krolak.windsurferweatherforecaster.services.WeatherForecastService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/forecast")
@RequiredArgsConstructor
public class ForecastController {
    private final WeatherForecastService weatherForecastService;

    @GetMapping
    public ResponseEntity<String> getForecast() {
        String response = weatherForecastService.performForecast();
        return ResponseEntity.ok(response);
    }
}
