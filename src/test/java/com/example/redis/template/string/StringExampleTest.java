package com.example.redis.template.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.TestPropertySource;

import java.time.Duration;

@SpringBootTest
@TestPropertySource("classpath:application.properties")
public class StringExampleTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void testRedisString() {
        String userId1 = "John Doe";
        BoundValueOperations bvoUserId1 = redisTemplate.boundValueOps("user:1");
        bvoUserId1.set(userId1);
        bvoUserId1.expire(Duration.ofHours(1L));

        String userId1Cached = redisTemplate.boundValueOps("user:1").get();

        Assertions.assertNotNull(userId1);
        Assertions.assertEquals(userId1, userId1Cached);
    }

}
