package com.example.redis.components;

import com.example.redis.configuration.WebClientConfig;
import com.example.redis.parameters.ParameterService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class WeatherWebClient implements WebClientInterface {

    private final WebClientConfig webClientConfig;
    private final ParameterService redisParameterServiceImpl;

    @Override
    @Cacheable(key = "'weather.region=' + #parameters.get(\"region\")",
            value = "120sExp")
    public Object get(Map<String, String> parameters) {
        final String urlByRegionUrl = redisParameterServiceImpl
                .getParameterByKey("URL_WEATHER_BY_REGION")
                .getValue();

        return webClientConfig
                .webClientWeather()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(urlByRegionUrl)
                        .path("/")
                        .path(parameters.get("region"))
                        .build()
                )
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        clientResponse -> clientResponse.bodyToMono(String.class).map(RuntimeException::new))
                .onStatus(HttpStatus::is5xxServerError,
                        clientResponse -> clientResponse.bodyToMono(String.class).map(RuntimeException::new))
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object post(Object object) {
        return new UnsupportedOperationException("");
    }

}
