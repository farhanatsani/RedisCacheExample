package com.example.redis.parameter.service;

import com.example.redis.parameter.entity.Parameter;
import com.example.redis.parameter.repository.ParameterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DatabaseParameterServiceImpl implements ParameterService {

    private ParameterRepository parameterRepository;

    public DatabaseParameterServiceImpl(ParameterRepository parameterRepository) {
        this.parameterRepository = parameterRepository;
    }

    @Override
    public Parameter getParameterByKey(String key) {
        Optional<Parameter> parameterOptional = parameterRepository
                .findByKey(key);

        if(parameterOptional.isEmpty()) {
            throw new NullPointerException("Parameter is not exist");
        }

        Parameter parameter = parameterOptional.get();

        return parameter;
    }

    @Override
    public void save(Parameter parameter) {
        Optional<Parameter> parameterOptional = parameterRepository.findByKey(parameter.getKey());
        if(parameterOptional.isEmpty()) {
            parameterRepository.save(parameter);
        }
    }

    @Override
    public void saveAll(List<Parameter> parameters) {
        for(Parameter initParameter: parameters) {
            save(initParameter);
        }
    }

}
