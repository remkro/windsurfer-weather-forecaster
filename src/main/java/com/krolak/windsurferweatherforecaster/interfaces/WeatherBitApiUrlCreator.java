package com.krolak.windsurferweatherforecaster.interfaces;

import java.util.List;

public interface WeatherBitApiUrlCreator {
    String create(String city, List<String> coordinates);
}
