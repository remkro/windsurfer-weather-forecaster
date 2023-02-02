package com.krolak.windsurferweatherforecaster.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeneralWeatherResponseDto {
    private String city_name;
    private String country_code;
    private List<WeatherDataDto> data;

    @Override
    public String toString() {
        return "GeneralWeatherResponseDto{" +
                "city_name='" + city_name + '\'' +
                ", country_code='" + country_code + '\'' +
                ", data=" + data +
                '}';
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WeatherDataDto {
        private LocalDate datetime;
        private double temp;
        private double wind_spd;

        @Override
        public String toString() {
            return "WeatherDataDto{" +
                    "datetime=" + datetime +
                    ", temp=" + temp +
                    ", wind_spd=" + wind_spd +
                    '}';
        }
    }
}

