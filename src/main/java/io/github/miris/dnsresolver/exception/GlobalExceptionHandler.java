package io.github.miris.dnsresolver.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleException(Exception exception) {

        log.error("Unknown/Undefined error occured.", exception);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "An unknown error occured. Please try again later.", "message",
                        exception.getMessage()));
    }

}
