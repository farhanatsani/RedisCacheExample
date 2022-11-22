package com.example.redis.weatherquake.component;

import com.example.redis.base.ParameterConstants;
import com.example.redis.components.WebClientInterface;
import com.example.redis.configuration.WebClientConfig;
import com.example.redis.parameter.service.ParameterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@AllArgsConstructor
public class QuakeWebClient implements WebClientInterface {
    private final WebClientConfig webClientConfig;
    private final ParameterService redisParameterServiceImpl;
    @Override
    @Cacheable(key = "'quake.info'",
            value = "120sExp")
    public Object get(Map<String, String> parameters) {
        final String urlQuake = redisParameterServiceImpl
                .getParameterByKey(ParameterConstants.URL_QUAKE_INFO)
                .getValue();

        return webClientConfig
                .weatherQuakeWebClient()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(urlQuake)
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
