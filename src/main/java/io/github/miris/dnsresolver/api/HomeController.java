package io.github.miris.dnsresolver.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    /**
     * Home page for the DNS Resolver Application.
     * 
     * @return Welcome message
     */
    @GetMapping
    public String home() {
        return "Welcome to the DNS Resolver Application!";
    }
}