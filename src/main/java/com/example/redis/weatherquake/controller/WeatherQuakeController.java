package com.example.redis.weatherquake.controller;

import com.example.redis.weatherquake.service.QuakeService;
import com.example.redis.weatherquake.service.WeatherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class WeatherQuakeController {
    private final WeatherService weatherServiceImpl;
    private final QuakeService quakeServiceImpl;
    @GetMapping(value = "/api/weathers/{region}")
    public ResponseEntity<?> getWeather(@PathVariable String region) {

        String weather = weatherServiceImpl.getWeatherByRegion(region);

        if(weather == null) {
            throw new NullPointerException("Empty response");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(weather);
    }
    @GetMapping(value = "/api/quake")
    public ResponseEntity<?> getQuakeInfo() {

        String weather = quakeServiceImpl.getQuakeInfo();

        if(weather == null) {
            throw new NullPointerException("Empty response");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(weather);
    }

}
