package com.example.redis.parameter.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;

@SuperBuilder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParameterDTO {
    private Integer id;
    private String key;
    private String description;
    @JsonAlias("param_group")
    private String paramGroup;
    private String value;
    @JsonAlias("value_type")
    private String valueType;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
}
