package com.example.redis.exception;

import com.example.redis.base.util.TimezoneUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    ResponseEntity<?> exceptionHandler(HttpStatus httpStatus, String uri, String message) {

        ErrorMessage errorMessage = ErrorMessage.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .date(LocalDateTime.now(TimezoneUtil.getZoneIdJakarta()))
                .message(message)
                .uri(uri)
                .build();

        return ResponseEntity.status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorMessage);
    }

    @ExceptionHandler(NullPointerException.class)
    ResponseEntity<?> nullExceptionHandler(NullPointerException ex, WebRequest request){
        return exceptionHandler(HttpStatus.NOT_FOUND, request.getDescription(false), ex.getMessage());
    }

    @ExceptionHandler(EntityExistsException.class)
    ResponseEntity<?> existExceptionHandler(EntityExistsException ex, WebRequest request){
        return exceptionHandler(HttpStatus.CONFLICT, request.getDescription(false), ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<?> runtimeExceptionHandler(RuntimeException ex, WebRequest request){
        return exceptionHandler(HttpStatus.INTERNAL_SERVER_ERROR, request.getDescription(false), ex.getMessage());
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    ResponseEntity<?> unsupportedExceptionHandler(UnsupportedOperationException ex, WebRequest request){
        return exceptionHandler(HttpStatus.SERVICE_UNAVAILABLE, request.getDescription(false), ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        String errorValidationMessage = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        ErrorMessage message = ErrorMessage.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .date(LocalDateTime.now(TimezoneUtil.getZoneIdJakarta()))
                .message(errorValidationMessage)
                .uri(request.getDescription(false))
                .build();

        return ResponseEntity.badRequest().body(message);
    }
}
