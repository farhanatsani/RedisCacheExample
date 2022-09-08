package com.example.redis.weather;

import com.example.redis.components.WeatherWebClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
public class WeatherController {

    private final WeatherWebClient weatherWebClient;

    @GetMapping(value = "/api/weathers/{region}")
    public ResponseEntity<?> getCustomer(@PathVariable String region) {

        Map<String, String> parameters = Collections.singletonMap("region", region);

        String weather = (String) weatherWebClient.get(parameters);

        log.info("Got response from server");
        log.info(weather);


        if(weather == null) {
            throw new NullPointerException("Empty response");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(weather);
    }
}
