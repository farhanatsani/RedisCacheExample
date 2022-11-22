package com.example.redis.base;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor
@Getter @Setter
public class BaseResponseDTO<T> {
    @JsonAlias("status_code")
    private Integer statusCode;
    @JsonAlias("date")
    private LocalDateTime date;
    private String message;
    private T data;
}
