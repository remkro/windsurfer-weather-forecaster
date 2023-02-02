package com.krolak.windsurferweatherforecaster.interfaces;

import com.krolak.windsurferweatherforecaster.dtos.GoodWeatherLocationDto;

import java.time.LocalDate;

public interface WindsurfingLocationFinder {
    GoodWeatherLocationDto findBestWeatherLocation(LocalDate date);
}
