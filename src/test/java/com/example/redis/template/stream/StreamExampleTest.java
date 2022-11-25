package com.example.redis.template.stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@TestPropertySource("classpath:application.properties")
public class StreamExampleTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void testRedisStream() {
        Map<String, String> sensor1Map = new HashMap<>();
        sensor1Map.put("sensor-id", "1");
        sensor1Map.put("temperature", "19.8");
        RecordId recordId1 = redisTemplate
                .opsForStream()
                .add("mystream", sensor1Map);
        System.out.println("record1 : " + recordId1);

        Map<String, String> sensor2Map = new HashMap<>();
        sensor2Map.put("sensor-id", "2");
        sensor2Map.put("temperature", "17.5");
        RecordId recordId2 = redisTemplate
                .opsForStream()
                .add("mystream", sensor2Map);
        System.out.println("record2 : " + recordId2);
    }
}
