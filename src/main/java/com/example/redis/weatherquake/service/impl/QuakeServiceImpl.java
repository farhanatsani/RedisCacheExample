package com.example.redis.weatherquake.service.impl;

import com.example.redis.weatherquake.component.QuakeWebClient;
import com.example.redis.weatherquake.service.QuakeService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
@AllArgsConstructor
public class QuakeServiceImpl implements QuakeService {
    private final QuakeWebClient quakeWebClient;
    @Override
    @Cacheable(key = "'quake.info'",
            value = "120sExp")
    public String getQuakeInfo() {
        Map<String, String> parameters = Collections.emptyMap();
        return (String) quakeWebClient.get(parameters);
    }
}
