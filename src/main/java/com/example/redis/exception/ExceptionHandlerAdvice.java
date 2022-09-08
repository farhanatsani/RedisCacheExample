package com.example.redis.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityExistsException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {

    ResponseEntity<?> exceptionHandler(HttpStatus httpStatus, String message) {

        log.info(message);
        Map<String, String> errors = new HashMap<>();
        errors.put("message", message);

        return ResponseEntity.status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errors);
    }

    @ExceptionHandler(NullPointerException.class)
    ResponseEntity<?> nullExceptionHandler(NullPointerException ex){
        return exceptionHandler(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(EntityExistsException.class)
    ResponseEntity<?> existExceptionHandler(EntityExistsException ex){
        return exceptionHandler(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<?> runtimeExceptionHandler(RuntimeException ex){
        return exceptionHandler(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    ResponseEntity<?> unsupportedExceptionHandler(UnsupportedOperationException ex){
        return exceptionHandler(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
    }

}
