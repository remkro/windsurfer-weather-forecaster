package com.krolak.windsurferweatherforecaster.services;

import com.krolak.windsurferweatherforecaster.dtos.GoodWeatherLocationDto;
import com.krolak.windsurferweatherforecaster.exceptions.NoLocationsProvidedException;
import com.krolak.windsurferweatherforecaster.interfaces.WeatherAnalysisService;
import com.krolak.windsurferweatherforecaster.interfaces.WeatherBitApiUrlCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WindsurfingLocationFinderImplTest {
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private WeatherAnalysisService analysisService;
    @Mock
    private WeatherBitApiUrlCreator urlCreator;
    @InjectMocks
    private WindsurfingLocationFinderImpl finder;

    @Test
    public void should_find_best_location_for_windsurfing() {
        Map<String, List<String>> locations = new HashMap<>();
        locations.put("Fortaleza", List.of("-3.71722", "-38.54306"));
        ReflectionTestUtils.setField(finder, "locations", locations);
        GoodWeatherLocationDto filteredLocation = new GoodWeatherLocationDto("Fortaleza", "BR",
                LocalDate.now(), 25.6, 7.5);
        when(analysisService.filterLocations(any(), any())).thenReturn(filteredLocation);
        GoodWeatherLocationDto bestWeatherLocation = finder.findBestWeatherLocation(LocalDate.now());
        assertEquals(filteredLocation.getCityName(), bestWeatherLocation.getCityName());
        assertEquals(filteredLocation.getCountry(), bestWeatherLocation.getCountry());
        assertEquals(filteredLocation.getDate(), bestWeatherLocation.getDate());
        assertEquals(filteredLocation.getAverageTemperature(), bestWeatherLocation.getAverageTemperature());
        assertEquals(filteredLocation.getWindSpeed(), bestWeatherLocation.getWindSpeed());
    }

    @Test
    public void should_throw_exception_when_there_are_no_locations_provided() {
        Map<String, List<String>> locations = new HashMap<>();
        ReflectionTestUtils.setField(finder, "locations", locations);
        Exception exception = assertThrows(NoLocationsProvidedException.class,
                () -> finder.findBestWeatherLocation(LocalDate.now()));
        String expectedMessage = "There are no locations to check!";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}