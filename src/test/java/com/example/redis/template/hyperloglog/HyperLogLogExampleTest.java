package com.example.redis.template.hyperloglog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:application.properties")
public class HyperLogLogExampleTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void testRedisHyperLogLog() {
        redisTemplate.opsForHyperLogLog().add("members", "123");
        redisTemplate.opsForHyperLogLog().add("members", "500");
        redisTemplate.opsForHyperLogLog().add("members", "12");

        Long count = redisTemplate.opsForHyperLogLog().size("members");
        System.out.print("members : " + count);
    }
}
