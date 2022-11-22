package com.example.redis.weatherquake.controller;

import com.example.redis.weatherquake.component.QuakeWebClient;
import com.example.redis.weatherquake.component.WeatherWebClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
public class WeatherQuakeController {
    private final WeatherWebClient weatherWebClient;
    private final QuakeWebClient quakeWebClient;
    @GetMapping(value = "/api/weathers/{region}")
    public ResponseEntity<?> getWeather(@PathVariable String region) {

        Map<String, String> parameters = Collections.singletonMap("region", region);
        String weather = (String) weatherWebClient.get(parameters);

        if(weather == null) {
            throw new NullPointerException("Empty response");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(weather);
    }
    @GetMapping(value = "/api/quake")
    public ResponseEntity<?> getQuakeInfo() {

        Map<String, String> parameters = Collections.singletonMap("region", "");
        String weather = (String) quakeWebClient.get(parameters);

        if(weather == null) {
            throw new NullPointerException("Empty response");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(weather);
    }

}
