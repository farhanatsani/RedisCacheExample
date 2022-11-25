package com.example.redis.template.bitfield;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:application.properties")
public class BitfieldExampleTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void testRedisBitfield() {
        redisTemplate.opsForValue().setBit("pings:2024-01-01-00:00", 123L, true);
        boolean sensor123ping = redisTemplate.opsForValue().getBit("pings:2024-01-01-00:00", 123L);
        System.out.println("sensor123ping : + " + sensor123ping);

        boolean sensor456ping = redisTemplate.opsForValue().getBit("pings:2024-01-01-00:00", 456L);
        System.out.println("sensor456ping : + " + sensor456ping);
    }
}
