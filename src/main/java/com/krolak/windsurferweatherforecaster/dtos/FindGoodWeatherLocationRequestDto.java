package com.krolak.windsurferweatherforecaster.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindGoodWeatherLocationRequestDto {
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
}
