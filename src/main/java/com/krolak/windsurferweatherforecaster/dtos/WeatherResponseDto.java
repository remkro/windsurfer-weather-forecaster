package com.krolak.windsurferweatherforecaster.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class WeatherResponseDto {
    private String city_name;
    private List<WeatherDataDto> data;

    @Override
    public String toString() {
        return "WeatherResponseDto{" +
                "city_name='" + city_name + '\'' +
                ", data=" + data +
                '}';
    }

    @Getter
    @Setter
    @NoArgsConstructor
    private static class WeatherDataDto {
        private LocalDate datetime;
        double temp;
        double wind_spd;

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

