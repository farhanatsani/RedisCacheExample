package com.example.redis.base;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class BaseController<T> {

    protected BaseResponseDTO createResponse(T t, Integer statusCode, String msg) {
        ZoneId jakartaTimezone = ZoneId.of("Asia/Jakarta");
        return BaseResponseDTO.builder()
                .date(LocalDateTime.now(jakartaTimezone))
                .statusCode(statusCode)
                .message(msg)
                .data(t)
                .build();
    }

}
