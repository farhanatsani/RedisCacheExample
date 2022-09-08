package com.example.redis.repository;

import com.example.redis.customers.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByPhoneNo(String phonneNo);
}
