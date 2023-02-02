package com.krolak.windsurferweatherforecaster.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldValidationErrorDto {
    private String field;
    private String errorMessage;
    private Object rejectedValue;
}
