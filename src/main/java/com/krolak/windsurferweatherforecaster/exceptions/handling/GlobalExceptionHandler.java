package com.krolak.windsurferweatherforecaster.exceptions.handling;

import com.krolak.windsurferweatherforecaster.dtos.ExceptionResponseDto;
import com.krolak.windsurferweatherforecaster.dtos.FieldValidationErrorDto;
import com.krolak.windsurferweatherforecaster.exceptions.NoGoodWeatherLocationFoundException;
import com.krolak.windsurferweatherforecaster.exceptions.NoLocationsProvidedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({NoGoodWeatherLocationFoundException.class})
    public ResponseEntity<ExceptionResponseDto> handleNoGoodWeatherLocationFoundException(Exception e) {
        ExceptionResponseDto response = new ExceptionResponseDto(e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler({NoLocationsProvidedException.class})
    public ResponseEntity<ExceptionResponseDto> handleNoLocationsProvidedException(Exception e) {
        ExceptionResponseDto response = new ExceptionResponseDto(e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<List<FieldValidationErrorDto>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldValidationErrorDto> fieldErrors = e.getFieldErrors()
                .stream()
                .map(fe -> new FieldValidationErrorDto(fe.getField(), fe.getDefaultMessage(), fe.getRejectedValue()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
    }
}
