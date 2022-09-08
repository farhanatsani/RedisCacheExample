package com.example.redis;

import com.example.redis.parameters.Parameter;
import com.example.redis.parameters.ParameterService;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;

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
			new Parameter("BASE_URL_CUACA_GEMPA", "Base URL Cuaca Gempa API", "Weather", "https://cuaca-gempa-rest-api.vercel.app/", "URL"),
			new Parameter("BASE_URL_CURRENCY", "Base URL Currency API", "Currency", "https://api-exchange-rates.herokuapp.com/", "URL"),
			new Parameter("BASE_URL_KBBI", "Base URL KBBI API", "Kamus", "https://kbbi-api-amm.herokuapp.com/", "URL"),
			new Parameter("URL_WEATHER_BY_REGION", "URL untuk menampilkan cuaca berdasarkan region", "Weather", "/weather", "URL"),
			new Parameter("URL_CURRENCY_CALCULATOR", "URL untuk menghitung nilai tukar mata uang", "Currency", "/calculator", "URL"),
			new Parameter("URL_KBBI_WORD", "URL untuk mencari kata pada KBBI", "Kamus", "/search", "URL"),
			new Parameter("HTTP_RESPONSE_TIMEOUT", "Durasi timeout Http Request", "UTIL", "60", "SECOND")
		);

		databaseParameterServiceImpl.saveAll(initParamExample);
	}

}
