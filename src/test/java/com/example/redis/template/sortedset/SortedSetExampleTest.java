package com.example.redis.template.sortedset;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.TestPropertySource;

import java.util.Iterator;
import java.util.Set;

@SpringBootTest
@TestPropertySource("classpath:application.properties")
public class SortedSetExampleTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void testRedisSortedSet() {
        redisTemplate.opsForZSet().add("leaderboard:1", "player1", 100);
        redisTemplate.opsForZSet().add("leaderboard:1", "player2", 75);
        redisTemplate.opsForZSet().add("leaderboard:1", "player3", 101);
        redisTemplate.opsForZSet().add("leaderboard:1", "player4", 15);
        redisTemplate.opsForZSet().add("leaderboard:1", "player2", 275);

        redisTemplate.opsForZSet().incrementScore("leaderboard:1", "player4", 150);

        // Get the top 3 players scores
        Set<ZSetOperations.TypedTuple<String>> top3scores = redisTemplate.opsForZSet()
                .reverseRangeWithScores("leaderboard:1", 0, 2);

        Iterator itScore = top3scores.iterator();
        while(itScore.hasNext()) {
            ZSetOperations.TypedTuple typedTuple = (ZSetOperations.TypedTuple) itScore.next();
            System.out.println(typedTuple.getValue() + " - " + typedTuple.getScore());
        }

        // What's the rank of Player 2?
        long player2rank = redisTemplate.opsForZSet().reverseRank("leaderboard:1", "player2");
        System.out.println("Player 2 Rank : " + player2rank);
    }
}
