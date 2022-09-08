package com.example.redis.parameters;

import java.util.List;

public interface ParameterService {

    Parameter getParameterByKey(String key);

    void save(Parameter parameter);

    void saveAll(List<Parameter> parameters);

}
