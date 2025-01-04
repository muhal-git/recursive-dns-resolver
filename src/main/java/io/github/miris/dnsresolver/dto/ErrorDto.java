package io.github.miris.dnsresolver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorDto {
    
    private String error;
    private String message;
}
