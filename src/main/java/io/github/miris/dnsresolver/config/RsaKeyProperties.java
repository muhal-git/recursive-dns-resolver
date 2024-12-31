package io.github.miris.dnsresolver.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for RSA public and private keys.
 */
@ConfigurationProperties("rsa")
public record RsaKeyProperties(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
    public RsaKeyProperties {
        if (publicKey == null || privateKey == null) {
            throw new IllegalArgumentException("Public and private keys must not be null");
        }
    }
}
