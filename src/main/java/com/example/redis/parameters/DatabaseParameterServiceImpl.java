package com.example.redis.parameters;

import com.example.redis.repository.ParameterRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
public class DatabaseParameterServiceImpl implements ParameterService {

    private ParameterRepository parameterRepository;

    public DatabaseParameterServiceImpl(ParameterRepository parameterRepository) {
        this.parameterRepository = parameterRepository;
    }

    @Override
    public Parameter getParameterByKey(String key) {
        return parameterRepository
                .findByKey(key)
                .get();
    }

    @Override
    public void save(Parameter parameter) {
        Parameter existParameter = getParameterByKey(parameter.getKey());
        if(existParameter != null) {
            throw new EntityExistsException("Parameter already exist");
        }

        parameterRepository.save(parameter);
    }

    @Override
    public void saveAll(List<Parameter> parameters) {
        List<Parameter> allParameter = parameterRepository.findAll();
        if(allParameter.isEmpty()) {
            parameterRepository.saveAll(allParameter);
        }

        for(Parameter initParameter: parameters) {
            Parameter availableParameter = parameterRepository.findByKey(initParameter.getKey()).get();
            if(availableParameter == null) {
                parameterRepository.save(availableParameter);
            }
        }
    }

}
