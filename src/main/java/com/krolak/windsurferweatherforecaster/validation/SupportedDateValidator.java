package com.krolak.windsurferweatherforecaster.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;

public class SupportedDateValidator implements ConstraintValidator<SupportedDate, LocalDate> {
    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedNow = LocalDate.now().format(formatter);
        LocalDate parsedNow = LocalDate.parse(formattedNow);
        String formattedDate = date.format(formatter);
        LocalDate parsedDate = LocalDate.parse(formattedDate);
        long difference = DAYS.between(parsedNow, parsedDate);
        return difference <= 15;
    }
}
