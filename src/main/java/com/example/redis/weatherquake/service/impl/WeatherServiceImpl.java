package com.example.redis.weatherquake.service.impl;

import com.example.redis.weatherquake.component.WeatherWebClient;
import com.example.redis.weatherquake.service.WeatherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final WeatherWebClient weatherWebClient;
    @Override
    @Cacheable(key = "'weather.region=' + #region",
            value = "120sExp")
    public String getWeatherByRegion(String region) {
        Map<String, String> parameters = Collections.singletonMap("region", region);
        return (String) weatherWebClient.get(parameters);
    }
}
