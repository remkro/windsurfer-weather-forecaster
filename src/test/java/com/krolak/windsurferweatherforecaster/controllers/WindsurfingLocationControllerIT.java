package com.krolak.windsurferweatherforecaster.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krolak.windsurferweatherforecaster.WindsurferWeatherForecasterApplication;
import com.krolak.windsurferweatherforecaster.dtos.FindGoodWeatherLocationRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = WindsurferWeatherForecasterApplication.class)
@AutoConfigureMockMvc
class WindsurfingLocationControllerIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void should_get_best_location_and_weather_for_windsurfing() throws Exception {
        FindGoodWeatherLocationRequestDto request = new FindGoodWeatherLocationRequestDto(LocalDate.now());
        String body = mapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/windsurfing-location/best")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cityName").isNotEmpty())
                .andExpect(jsonPath("$.country").isNotEmpty())
                .andExpect(jsonPath("$.date").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$.averageTemperature").isNumber())
                .andExpect(jsonPath("$.windSpeed").isNumber());
    }

    @Test
    public void should_get_validation_error_if_provided_date_is_more_than_16_days() throws Exception {
        FindGoodWeatherLocationRequestDto request = new FindGoodWeatherLocationRequestDto(LocalDate.now().plusDays(16));
        String body = mapper.writeValueAsString(request);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/windsurfing-location/best")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        String responseAsString = result.getResponse().getContentAsString();
        assertTrue(responseAsString.contains("DATE_MUST_BE_IN_SUPPORTED_RANGE"));
    }

    @Test
    public void should_get_validation_error_if_provided_date_is_in_the_past() throws Exception {
        FindGoodWeatherLocationRequestDto request = new FindGoodWeatherLocationRequestDto(LocalDate.now().minusDays(10));
        String body = mapper.writeValueAsString(request);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/windsurfing-location/best")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        String responseAsString = result.getResponse().getContentAsString();
        assertTrue(responseAsString.contains("DATE_MUST_BE_PRESENT_OR_FUTURE"));
    }

    @Test
    public void should_get_validation_error_if_provided_date_is_null() throws Exception {
        FindGoodWeatherLocationRequestDto request = new FindGoodWeatherLocationRequestDto(null);
        String body = mapper.writeValueAsString(request);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/windsurfing-location/best")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        String responseAsString = result.getResponse().getContentAsString();
        assertTrue(responseAsString.contains("DATE_CANNOT_BE_NULL"));
    }
}