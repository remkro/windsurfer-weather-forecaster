package com.krolak.windsurferweatherforecaster.services;

import com.krolak.windsurferweatherforecaster.dtos.GeneralWeatherResponseDto;
import com.krolak.windsurferweatherforecaster.dtos.GoodWeatherLocationDto;
import com.krolak.windsurferweatherforecaster.exceptions.NoGoodWeatherLocationFoundException;
import com.krolak.windsurferweatherforecaster.interfaces.WeatherAnalysisService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class WeatherAnalysisServiceImpl implements WeatherAnalysisService {

    public GoodWeatherLocationDto filterLocations(List<GeneralWeatherResponseDto> weathers, LocalDate date) {
        //nie musisz tworzyc zmiennej, zamiast tego daj od razu createFlatList(weathers).stream()
        List<GoodWeatherLocationDto> unfilteredWeatherList = createFlatList(weathers);
        return unfilteredWeatherList.stream()
                .filter(u -> u.getDate().equals(date))
                .filter(u -> checkForGoodConditions(u.getAverageTemperature(), u.getWindSpeed()))
                .max(Comparator.comparingDouble(u -> calculateUsingSpecialFormula(u.getAverageTemperature(), u.getWindSpeed())))
                .orElseThrow(() -> new NoGoodWeatherLocationFoundException("Unable to find location with good weather!"));
    }

    private List<GoodWeatherLocationDto> createFlatList(List<GeneralWeatherResponseDto> weathers) {
        List<GoodWeatherLocationDto> goodWeatherLocationDtoList = new ArrayList<>();
        //petla w petli nie jest zbyt dobrym sposobem, moze lepiej uzyc streamow?
        for (GeneralWeatherResponseDto weather : weathers) {
            for (GeneralWeatherResponseDto.WeatherDataDto data : weather.getData()) {
                goodWeatherLocationDtoList.add(new GoodWeatherLocationDto(
                        weather.getCity_name(),
                        weather.getCountry_code(),
                        data.getDatetime(),
                        data.getTemp(),
                        data.getWind_spd()
                ));
            }
        }
        return goodWeatherLocationDtoList;
    }

    private boolean checkForGoodConditions(double temp, double wind) {
        //wyciagnalbym te wartosci 5 35 18 to stalych w klasie
        boolean goodTemp = temp >= 5 && temp <= 35;
        boolean goodWind = wind >= 5 && wind <= 18;
        return goodTemp && goodWind;
    }

    //3 do stalych w klasie
    private double calculateUsingSpecialFormula(double temp, double wind) {
        return wind * 3 + temp;
    }
}
