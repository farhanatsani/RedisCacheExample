package com.example.redis.repository;

import com.example.redis.parameters.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParameterRepository extends JpaRepository<Parameter, Integer> {

    Optional<Parameter> findByKey(String key);

}
