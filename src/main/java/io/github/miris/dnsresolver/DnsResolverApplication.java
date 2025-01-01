package io.github.miris.dnsresolver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import io.github.miris.dnsresolver.config.RsaKeyProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
@EnableAspectJAutoProxy
public class DnsResolverApplication {

	public static void main(String[] args) {
		SpringApplication.run(DnsResolverApplication.class, args);
	}

}
