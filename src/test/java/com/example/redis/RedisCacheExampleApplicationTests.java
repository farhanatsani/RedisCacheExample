package com.example.redis;

import com.example.redis.parameters.Parameter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisCacheExampleApplicationTests {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Test
	void contextLoads() {

		Parameter parameter1 = (Parameter) redisTemplate.opsForValue().get("60sExp::getParameterByKey=URL_WEATHER_BY_REGION");
		System.out.println(parameter1);

		Parameter parameter2 = parameter1;
		parameter2.setDescription("Tes Ubah pamareter 2");
		redisTemplate.opsForValue().set("TES", parameter2);

	}

}
