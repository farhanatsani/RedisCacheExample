package com.example.redis.customer.repository;

import com.example.redis.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByPhoneNo(String phonneNo);
}
