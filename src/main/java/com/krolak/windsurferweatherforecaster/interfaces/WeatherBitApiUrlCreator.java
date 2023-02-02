package com.krolak.windsurferweatherforecaster.interfaces;

import java.util.List;
import java.util.Map;

public interface WeatherBitApiUrlCreator {
    String create(String city, List<String> coordinates);
}
