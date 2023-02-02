package com.krolak.windsurferweatherforecaster.interfaces;

import java.util.List;
import java.util.Map;

public interface WeatherBitApiUrlCreator {
    String create(Map.Entry<String, List<String>> locationsMap);
}
