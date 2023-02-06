package com.krolak.windsurferweatherforecaster.interfaces;

import com.krolak.windsurferweatherforecaster.dtos.GeneralWeatherResponseDto;
import com.krolak.windsurferweatherforecaster.dtos.GoodWeatherLocationDto;

import java.time.LocalDate;
import java.util.List;

//daj do
public interface WeatherAnalysisService {
    GoodWeatherLocationDto filterLocations(List<GeneralWeatherResponseDto> weathers, LocalDate date);
}
