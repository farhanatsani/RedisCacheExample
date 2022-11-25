package com.example.redis.template.geospatial;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@SpringBootTest
@TestPropertySource("classpath:application.properties")
public class GeospatialExampleTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void testRedisGeospatial() {
        Point pointBranch1 = new Point(106.659399, -6.276169);
        Point pointBranch2 = new Point(106.670515, -6.270527 );

        redisTemplate.opsForGeo().add("branches", pointBranch1, "pointBranch1");
        redisTemplate.opsForGeo().add("branches", pointBranch2, "pointBranch2");

        List<Point> point1ListCache = redisTemplate.opsForGeo().position("pointBranch1");
        for(Point point: point1ListCache) {
            System.out.print("PointBranch-1 : " + point.getX() + ", " + point.getY());
        }

        Distance distance = redisTemplate.opsForGeo().distance("branches", "pointBranch1", "pointBranch2");
        double d = distance.getValue();

    }
}
