package io.github.miris.dnsresolver.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorDto {
    
    private String error;
    private String message;
}
