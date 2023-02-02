package com.krolak.windsurferweatherforecaster.controllers;

import com.krolak.windsurferweatherforecaster.dtos.FindGoodWeatherLocationRequestDto;
import com.krolak.windsurferweatherforecaster.dtos.GoodWeatherLocationDto;
import com.krolak.windsurferweatherforecaster.interfaces.WindsurfingLocationFinder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/windsurfing-location")
@RequiredArgsConstructor
public class WindsurfingLocationController {
    private final WindsurfingLocationFinder finder;

    @GetMapping("/best")
    public ResponseEntity<GoodWeatherLocationDto> getBestLocation(@Valid @RequestBody FindGoodWeatherLocationRequestDto request) {
        GoodWeatherLocationDto response = finder.findBestWeatherLocation(request.getDate());
        return ResponseEntity.ok(response);
    }
}
