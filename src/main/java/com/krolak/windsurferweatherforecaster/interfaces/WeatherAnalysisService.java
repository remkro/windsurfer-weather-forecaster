package com.krolak.windsurferweatherforecaster.interfaces;

import com.krolak.windsurferweatherforecaster.dto.GeneralWeatherResponseDto;
import com.krolak.windsurferweatherforecaster.dto.GoodWeatherLocationDto;

import java.time.LocalDate;
import java.util.List;

public interface WeatherAnalysisService {
    GoodWeatherLocationDto filterLocations(List<GeneralWeatherResponseDto> weathers, LocalDate date);
}
