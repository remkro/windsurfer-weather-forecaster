package com.krolak.windsurferweatherforecaster.configuration;

import com.krolak.windsurferweatherforecaster.interfaces.WeatherBitApiUrlCreator;
import com.krolak.windsurferweatherforecaster.service.CityWeatherBitApiUrlCreator;
import com.krolak.windsurferweatherforecaster.service.CoordinatesWeatherBitApiUrlCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Value("${checkMethod}")
    private String urlCreationMethod;

    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    WeatherBitApiUrlCreator getWeatherBitApiUrlCreator() {
        if (urlCreationMethod.equalsIgnoreCase("cityName"))
            return new CityWeatherBitApiUrlCreator();
        return new CoordinatesWeatherBitApiUrlCreator();
    }
}
