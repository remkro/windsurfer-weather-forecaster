package com.krolak.windsurferweatherforecaster.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CityWeatherBitApiUrlCreatorTest {
    private final CityWeatherBitApiUrlCreator creator = new CityWeatherBitApiUrlCreator();

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(creator, "apiUrl", "https://api.weatherbit.io/v2.0/forecast/daily?");
        ReflectionTestUtils.setField(creator, "apiKey", "6e75e95ca7084679aa78dfb9daf2b5c2");
    }

    @Test
    public void should_create_proper_url() {
        String actualUrl = creator.create("Jastarnia", List.of("54.69606", "18.67873"));
        String expectedUrl = "https://api.weatherbit.io/v2.0/forecast/daily?city=Jastarnia&" +
                "key=6e75e95ca7084679aa78dfb9daf2b5c2";
        assertEquals(expectedUrl, actualUrl);
    }
}