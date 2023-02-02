package com.krolak.windsurferweatherforecaster.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = SupportedDateValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SupportedDate {

    String message() default "Provided date is outside supported range!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
