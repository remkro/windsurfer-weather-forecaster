package com.krolak.windsurferweatherforecaster.controllers;

import com.krolak.windsurferweatherforecaster.dtos.GoodWeatherLocationDto;
import com.krolak.windsurferweatherforecaster.interfaces.WindsurfingLocationFinder;
import com.krolak.windsurferweatherforecaster.validation.SupportedDate;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/windsurfing-location")
@RequiredArgsConstructor
@Validated
public class WindsurfingLocationController {
    private final WindsurfingLocationFinder finder;

    @GetMapping()
    public ResponseEntity<GoodWeatherLocationDto>
    getBestLocation(@RequestParam("date")
                    @DateTimeFormat(pattern = "yyyy-MM-dd")
                    @FutureOrPresent(message = "DATE_MUST_BE_PRESENT_OR_FUTURE")
                    @SupportedDate(message = "DATE_MUST_BE_IN_SUPPORTED_RANGE") LocalDate date) {
        GoodWeatherLocationDto response = finder.findBestWeatherLocation(date);
        //jesli dajesz @RestController to nie musisz zwracac ResponseEntity<T> tylko moze byc samo T, domyslna odpowiedz i tak bedzie 200
        return ResponseEntity.ok(response);
    }
}
