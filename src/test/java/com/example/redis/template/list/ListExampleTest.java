package com.example.redis.template.list;

import com.example.redis.customer.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.TestPropertySource;

import java.time.Duration;
import java.util.List;

@SpringBootTest
@TestPropertySource("classpath:application.properties")
public class ListExampleTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void testRedisList() {
        /*
            List of String example
        */

        redisTemplate.opsForList().leftPush("employees", "Adi");
        redisTemplate.opsForList().leftPush("employees", "Rara");
        redisTemplate.opsForList().leftPush("employees", "Hendra");

        List<String> stringList = redisTemplate.opsForList().range("employees", 0, 10);
        for(String s: stringList) {
            System.out.println("s " + s);
        }



//        List<Customer> customers = List.of(
//                Customer.builder().id(1).name("John").phoneNo("0811111").address("Jakarta").build(),
//                Customer.builder().id(2).name("Doe").phoneNo("0811112").address("Bandung").build()
//        );
//
//        BoundListOperations blo = redisTemplate.boundListOps("customers");
//        blo.expire(Duration.ofHours(1L));
//        blo.leftPushAll(customers);
//
//        Customer customerAdd1 = Customer.builder().id(1).name("JohnAdd1").phoneNo("0811113").address("Jakarta").build();
//        blo.leftPush(customerAdd1);
//
//        Customer customerAdd2 = Customer.builder().id(1).name("JohnAdd2").phoneNo("0811113").address("Jakarta").build();
//        blo.leftPush(customerAdd2);

    }

}
