package com.example.urlshortener.exception;

import java.time.Instant;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ShortUrlNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleShortUrlNotFound(
            ShortUrlNotFoundException exception
    ) {

        return Map.of(
                "status", 404,
                "message", exception.getMessage(),
                "timestamp", Instant.now()
        );
    }
}