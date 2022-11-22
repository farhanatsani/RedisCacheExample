package com.example.redis.parameter.service;

import com.example.redis.parameter.entity.Parameter;
import com.example.redis.parameter.repository.ParameterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RedisParameterServiceImpl implements ParameterService {

    private ParameterRepository parameterRepository;

    public RedisParameterServiceImpl(ParameterRepository parameterRepository) {
        this.parameterRepository = parameterRepository;
    }

    @Override
    @Cacheable(key = "#root.methodName + '=' +#key",
            value = "60sExp")
    public Parameter getParameterByKey(String key) {

        Parameter parameter = parameterRepository
                .findByKey(key)
                .get();

        return parameter;
    }

    @Override
    public void save(Parameter parameter) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public void saveAll(List<Parameter> parameters) {
        throw new UnsupportedOperationException("Operation not supported");
    }

}
