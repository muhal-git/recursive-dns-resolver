package io.github.miris.dnsresolver.api.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.miris.dnsresolver.service.JwtService;
import lombok.RequiredArgsConstructor;

/**
 * Controller for handling authentication requests.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final JwtService tokenService;

    /**
     * Generates a token for the authenticated user.
     * 
     * @param authentication the authenticated user
     * @return the generated token
     */
    @PostMapping("/login")
    public String login(Authentication authentication) {
        LOG.debug("Token requested for user: '{}'", authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOG.debug("Token granted: {}", token);
        return token;
    }

}