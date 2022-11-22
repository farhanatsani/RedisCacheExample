package com.example.redis.parameter.mapper;

import com.example.redis.parameter.dto.ParameterDTO;
import com.example.redis.parameter.entity.Parameter;

public class ParameterMapper {
    private ParameterMapper() {}
    public static ParameterDTO toParameterDTO(Parameter parameter) {
        ParameterDTO parameterDTO = ParameterDTO.builder()
                .id(parameter.getId())
                .key(parameter.getKey())
                .value(parameter.getValue())
                .description(parameter.getDescription())
                .paramGroup(parameter.getParamGroup())
                .createDateTime(parameter.getCreateDateTime())
                .updateDateTime(parameter.getUpdateDateTime())
                .build();
        return parameterDTO;
    }
    public static Parameter toParameter(ParameterDTO parameterDTO) {
        Parameter parameter = Parameter.builder()
                .key(parameterDTO.getKey())
                .value(parameterDTO.getValue())
                .description(parameterDTO.getDescription())
                .paramGroup(parameterDTO.getParamGroup())
                .createDateTime(parameterDTO.getCreateDateTime())
                .updateDateTime(parameterDTO.getUpdateDateTime())
                .build();
        return null;
    }
}
