package com.example.redis.configuration;

import com.example.redis.base.ParameterConstants;
import com.example.redis.parameter.service.ParameterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Slf4j
@Configuration
@AllArgsConstructor
public class WebClientConfig {
    private final ParameterService redisParameterServiceImpl;
    @Bean
    public ExchangeStrategies exchangeStrategies() {
        final int size = 16 * 1024 * 1024;
        return ExchangeStrategies
                .builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();
    }
    @Bean
    public HttpClient httpClient() {
        final Integer timeout = Integer.parseInt(redisParameterServiceImpl
                .getParameterByKey(ParameterConstants.HTTP_RESPONSE_TIMEOUT)
                .getValue());

        return HttpClient
                .create()
                .responseTimeout(Duration.ofSeconds(timeout));
    }
    @Bean
    public ReactorClientHttpConnector reactorClientHttpConnector() {
        return new ReactorClientHttpConnector(httpClient());
    }
    @Bean
    public WebClient weatherQuakeWebClient() {
        final String urlBase = redisParameterServiceImpl
                .getParameterByKey(ParameterConstants.BASE_URL_CUACA_GEMPA)
                .getValue();

        return WebClient.builder()
                .clientConnector(reactorClientHttpConnector())
                .exchangeStrategies(exchangeStrategies())
                .baseUrl(urlBase)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    public WebClient webClientCurrency() {
        final String urlBase = redisParameterServiceImpl
                .getParameterByKey(ParameterConstants.BASE_URL_CURRENCY)
                .getValue();

        return WebClient.builder()
                .clientConnector(reactorClientHttpConnector())
                .exchangeStrategies(exchangeStrategies())
                .baseUrl(urlBase)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    public WebClient webClientKbbi() {
        final String urlBase = redisParameterServiceImpl
                .getParameterByKey(ParameterConstants.BASE_URL_KBBI)
                .getValue();

        return WebClient.builder()
                .clientConnector(reactorClientHttpConnector())
                .exchangeStrategies(exchangeStrategies())
                .baseUrl(urlBase)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
