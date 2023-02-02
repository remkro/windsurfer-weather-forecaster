package com.krolak.windsurferweatherforecaster.controllers;

import com.krolak.windsurferweatherforecaster.dtos.FindGoodWeatherLocationRequestDto;
import com.krolak.windsurferweatherforecaster.dtos.GoodWeatherLocationDto;
import com.krolak.windsurferweatherforecaster.services.WeatherForecastService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/forecast")
@RequiredArgsConstructor
public class ForecastController {
    private final WeatherForecastService weatherForecastService;

    @GetMapping
    public ResponseEntity<GoodWeatherLocationDto> getForecast(@Valid @RequestBody FindGoodWeatherLocationRequestDto request) {
        GoodWeatherLocationDto response = weatherForecastService.performForecast(request.getDate());
        return ResponseEntity.ok(response);
    }
}
