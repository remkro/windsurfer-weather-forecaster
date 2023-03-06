package com.krolak.windsurferweatherforecaster.service;

import com.krolak.windsurferweatherforecaster.dto.GeneralWeatherResponseDto;
import com.krolak.windsurferweatherforecaster.dto.GoodWeatherLocationDto;
import com.krolak.windsurferweatherforecaster.exception.NoGoodWeatherLocationFoundException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WeatherAnalysisServiceImplTest {
    private final WeatherAnalysisServiceImpl service = new WeatherAnalysisServiceImpl();

    @Test
    public void should_filter_best_location() {
        List<GeneralWeatherResponseDto> weathers = getExampleData();
        GoodWeatherLocationDto goodWeatherLocationDto = service.filterLocations(weathers, LocalDate.now());
        assertEquals(goodWeatherLocationDto.getCityName(), "Fortaleza");
        assertEquals(goodWeatherLocationDto.getDate(), LocalDate.now());
        assertEquals(goodWeatherLocationDto.getAverageTemperature(), 25.6);
        assertEquals(goodWeatherLocationDto.getWindSpeed(), 7.5);
    }

    @Test
    public void should_throw_exception_when_there_is_no_location_with_good_weather_found() {
        List<GeneralWeatherResponseDto> weathers = getExampleDataWithBadWeather();
        Exception exception = assertThrows(NoGoodWeatherLocationFoundException.class,
                () -> service.filterLocations(weathers, LocalDate.now()));
        String expectedMessage = "Unable to find location with good weather!";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    private List<GeneralWeatherResponseDto> getExampleData() {
        GeneralWeatherResponseDto.WeatherDataDto data1 =
                new GeneralWeatherResponseDto.WeatherDataDto(LocalDate.now(), 15.0, 10.0);
        GeneralWeatherResponseDto weather1 =
                new GeneralWeatherResponseDto("Jastarnia", "PL", List.of(data1));

        GeneralWeatherResponseDto.WeatherDataDto data2 =
                new GeneralWeatherResponseDto.WeatherDataDto(LocalDate.now(), 22.3, 4.5);
        GeneralWeatherResponseDto weather2 =
                new GeneralWeatherResponseDto("Bridgetown", "BB", List.of(data2));

        GeneralWeatherResponseDto.WeatherDataDto data3 =
                new GeneralWeatherResponseDto.WeatherDataDto(LocalDate.now(), 25.6, 7.5);
        GeneralWeatherResponseDto weather3 =
                new GeneralWeatherResponseDto("Fortaleza", "BR", List.of(data3));

        List<GeneralWeatherResponseDto> weathers = new ArrayList<>();
        weathers.add(weather1);
        weathers.add(weather2);
        weathers.add(weather3);

        return weathers;
    }

    private List<GeneralWeatherResponseDto> getExampleDataWithBadWeather() {
        GeneralWeatherResponseDto.WeatherDataDto data1 =
                new GeneralWeatherResponseDto.WeatherDataDto(LocalDate.now(), 3.0, 4.0);
        GeneralWeatherResponseDto weather1 =
                new GeneralWeatherResponseDto("Jastarnia", "PL", List.of(data1));

        GeneralWeatherResponseDto.WeatherDataDto data2 =
                new GeneralWeatherResponseDto.WeatherDataDto(LocalDate.now(), -5.0, 2.0);
        GeneralWeatherResponseDto weather2 =
                new GeneralWeatherResponseDto("Bridgetown", "BB", List.of(data2));

        List<GeneralWeatherResponseDto> weathers = new ArrayList<>();
        weathers.add(weather1);
        weathers.add(weather2);

        return weathers;
    }
}