package com.example.redis.template.set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.Set;

@SpringBootTest
@TestPropertySource("classpath:application.properties")
public class SetExampleTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void testRedisSet() {
        redisTemplate.opsForSet().add("tnkbs", "B1234SID");
        redisTemplate.opsForSet().add("tnkbs", "B4321SID");
        redisTemplate.opsForSet().add("tnkbs", "B1234SID");                                 // This will ignore, since there is same as first data

        boolean tnkbAvailable = redisTemplate.opsForSet().isMember("tnkbs", "B1234SID");
        System.out.println("tnkbAvailable : " + tnkbAvailable);

        System.out.println("Print all tnkb");
        Set<String> tnkbSet = redisTemplate.opsForSet().members("tnkbs");
        for(String s: tnkbSet) {
            System.out.println(s);
        }
    }

}
