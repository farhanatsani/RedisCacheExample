package com.example.redis.parameter.service;

import com.example.redis.parameter.entity.Parameter;

import java.util.List;

public interface ParameterService {

    Parameter getParameterByKey(String key);

    void save(Parameter parameter);

    void saveAll(List<Parameter> parameters);

}
