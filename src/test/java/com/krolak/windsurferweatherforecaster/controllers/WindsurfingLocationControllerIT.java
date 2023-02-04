package com.krolak.windsurferweatherforecaster.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krolak.windsurferweatherforecaster.WindsurferWeatherForecasterApplication;
import com.krolak.windsurferweatherforecaster.dtos.GoodWeatherLocationDto;
import com.krolak.windsurferweatherforecaster.interfaces.WindsurfingLocationFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = WindsurferWeatherForecasterApplication.class)
@AutoConfigureMockMvc
class WindsurfingLocationControllerIT {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WindsurfingLocationFinder finder;

    @Test
    public void should_get_best_location_and_weather_for_windsurfing() throws Exception {
        LocalDate date = LocalDate.now();
        when(finder.findBestWeatherLocation(date)).thenReturn(
                new GoodWeatherLocationDto("Bridgetown", "BB", date, 25.7, 9.9));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/windsurfing-location?date=" + date)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cityName").value("Bridgetown"))
                .andExpect(jsonPath("$.country").value("BB"))
                .andExpect(jsonPath("$.date").value(date.toString()))
                .andExpect(jsonPath("$.averageTemperature").value(25.7))
                .andExpect(jsonPath("$.windSpeed").value(9.9));
    }

    @Test
    public void should_get_validation_error_if_provided_date_is_more_than_16_days() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/windsurfing-location?date=" +
                                LocalDate.now().plusDays(16))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        String responseAsString = result.getResponse().getContentAsString();
        assertTrue(responseAsString.contains("DATE_MUST_BE_IN_SUPPORTED_RANGE"));
    }

    @Test
    public void should_get_validation_error_if_provided_date_is_in_the_past() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/windsurfing-location?date=" +
                                LocalDate.now().minusDays(16))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        String responseAsString = result.getResponse().getContentAsString();
        assertTrue(responseAsString.contains("DATE_MUST_BE_PRESENT_OR_FUTURE"));
    }
}