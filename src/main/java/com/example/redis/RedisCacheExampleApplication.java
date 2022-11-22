package com.example.redis;

import com.example.redis.parameter.entity.Parameter;
import com.example.redis.parameter.service.ParameterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@SpringBootApplication
@AllArgsConstructor
public class RedisCacheExampleApplication {
	private final ParameterService databaseParameterServiceImpl;
	public static void main(String[] args) {
		SpringApplication.run(RedisCacheExampleApplication.class, args);
	}
	@PostConstruct
	public void initConstructParameter() {
		List<Parameter> initParamExample = List.of(
			Parameter.builder().key("HTTP_RESPONSE_TIMEOUT").description("Durasi timeout Http Request").paramGroup("UTIL").value("60").valueType("SECOND").build(),
			Parameter.builder().key("BASE_URL_CUACA_GEMPA").description("Base URL Cuaca Gempa API").paramGroup("Weather").value("https://cuaca-gempa-rest-api.vercel.app/").valueType("URL").build(),
			Parameter.builder().key("BASE_URL_CURRENCY").description("Base URL Currency API").paramGroup("Currency").value("https://api-exchange-rates.herokuapp.com/").valueType("URL").build(),
			Parameter.builder().key("BASE_URL_KBBI").description("Base URL KBBI API").paramGroup("Kamus").value("https://kbbi-api-amm.herokuapp.com/").valueType("URL").build(),
			Parameter.builder().key("URL_WEATHER_BY_REGION").description("URL untuk menampilkan cuaca berdasarkan region").paramGroup("Weather").value("/weather").valueType("URL").build(),
			Parameter.builder().key("URL_QUAKE_INFO").description("URL untuk menampilkan info gempa").paramGroup("Quake").value("/quake").valueType("URL").build(),
			Parameter.builder().key("URL_CURRENCY_CALCULATOR").description("URL untuk menghitung nilai tukar mata uang").paramGroup("Currency").value("/calculator").valueType("URL").build(),
			Parameter.builder().key("URL_KBBI_WORD").description("URL untuk mencari kata pada KBBI").paramGroup("Kamus").value("/search").valueType("URL").build()
		);
		databaseParameterServiceImpl.saveAll(initParamExample);
	}
}
