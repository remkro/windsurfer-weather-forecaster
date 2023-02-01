package com.krolak.windsurferweatherforecaster.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoodWeatherLocationDto {
    private String cityName;
    private String country;
    private LocalDate date;
    private double averageTemperature;
    private double windSpeed;

    @Override
    public String toString() {
        return "GoodWeatherLocationDto{" +
                "cityName='" + cityName + '\'' +
                ", country='" + country + '\'' +
                ", date=" + date +
                ", averageTemperature=" + averageTemperature +
                ", windSpeed=" + windSpeed +
                '}';
    }
}
