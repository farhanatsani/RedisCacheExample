package com.example.redis.template.bitmap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:application.properties")
public class BitmapExampleTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void testRedisBitmap() {
        BitFieldSubCommands bitFieldSubCommands = BitFieldSubCommands.create();
        bitFieldSubCommands.set(BitFieldSubCommands.BitFieldType.INT_32);
        redisTemplate.opsForValue().bitField("player:1:stats", bitFieldSubCommands);
    }
}
