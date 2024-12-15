package io.github.miris.dnsresolver.api.dns;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resolve")
public class DnsController {

    /**
     * Resolves DNS queries recursively for specified domain name and record type.
     * @param name Domain name to resolve
     * @param type DNS record type (e.g. A, AAAA, TXT, CNAME, CAA, etc.)
     * @return DNS resolution result
     */
    @GetMapping
    public ResponseEntity<?> resolveDns(
        @RequestParam(value = "name", required = true) String name,
        @RequestParam(value = "type", required = true) String type) {
        
        // TODO: Implement actual DNS resolution
        return ResponseEntity.ok(Map.of(
            "query", Map.of(
                "name", name,
                "type", type
            ),
            "status", "pending"
        ));
    }
}
