package io.github.miris.dnsresolver.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.xbill.DNS.TextParseException;

import lombok.extern.slf4j.Slf4j;

import io.github.miris.dnsresolver.dto.ErrorDto;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleException(Exception exception) {

        log.error("Unknown/Undefined error occured.", exception);

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ErrorDto.builder().error("Please try again later.").build());
    }

    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    public ResponseEntity<Object> handleException(HttpRequestMethodNotSupportedException exception) {

        log.error("HttpRequestMethodNotSupportedException occured.", exception);

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ErrorDto.builder().error("Method not allowed.").message(exception.getMessage()).build());
    }

    @ExceptionHandler({ TextParseException.class })
    public ResponseEntity<Object> handleException(TextParseException exception) {

        log.error("TextParseException occured.", exception);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorDto.builder().error("Invalid domain or record type.").message(exception.getMessage()).build());
    }

}
