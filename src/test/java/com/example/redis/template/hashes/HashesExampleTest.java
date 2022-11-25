package com.example.redis.template.hashes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@TestPropertySource("classpath:application.properties")
public class HashesExampleTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void testRedisHashes() {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("id", "1");
        userMap.put("username", "user1");
        userMap.put("firstName", "Elisa");
        userMap.put("lastName", "Margareth");
        userMap.put("email", "elisa@email.com");
        userMap.put("address", "Jakarta");
        redisTemplate.opsForHash().putAll("users:1", userMap);
        redisTemplate.opsForHash().put("users:1", "phoneNo", "08111111");

        String user1FirstNameCache = (String) redisTemplate.opsForHash().get("users:1", "firstName");
        System.out.println("user1FirstNameCache : " + user1FirstNameCache);

        String user1PhoneNoCache = (String) redisTemplate.opsForHash().get("users:1", "phoneNo");
        System.out.println("user1PhoneNoCache : " + user1PhoneNoCache);
    }
}
