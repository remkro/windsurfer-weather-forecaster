package com.krolak.windsurferweatherforecaster.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.krolak.windsurferweatherforecaster.validation.SupportedDate;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "DATE_CANNOT_BE_NULL")
    @FutureOrPresent(message = "DATE_MUST_BE_PRESENT_OR_FUTURE")
    @SupportedDate(message = "DATE_MUST_BE_IN_SUPPORTED_RANGE")
    private LocalDate date;
}
