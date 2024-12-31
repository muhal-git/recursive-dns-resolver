package io.github.miris.dnsresolver.api.dns;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xbill.DNS.TextParseException;

import io.github.miris.dnsresolver.service.DnsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller for DNS resolution API.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/resolve")
public class DnsResolverController {

        private final DnsService dnsService;

        /**
         * Resolves DNS queries recursively for specified domain name and record type.
         * 
         * @param name Domain name to resolve
         * @param type DNS record type (e.g. A, AAAA, TXT, CNAME, CAA, etc.)
         * @return DNS resolution result
         * @throws TextParseException if the domain name is invalid
         * 
         * Versioning done using path parameter.
         */
        @GetMapping(path = "/v1")
        public ResponseEntity<Object> resolveDns(
                        @RequestParam(value = "name", required = true) String name,
                        @RequestParam(value = "type", required = true) String type) throws TextParseException {

                log.debug("Resolving DNS query for domain: '{}' and type: '{}'", name, type);
                return ResponseEntity.ok(dnsService.resolveDns(name, type));
        }

        /**
         * Resolves DNS queries recursively for specified domain name and record type.
         * 
         * @param name Domain name to resolve
         * @param type DNS record type (e.g. A, AAAA, TXT, CNAME, CAA, etc.)
         * @return DNS resolution result
         * @throws TextParseException if the domain name is invalid
         * 
         * Versioning done using header.
         */
        @GetMapping(produces = "application/vnd.dnsresolver.v2+json")
        public ResponseEntity<Object> resolveDnsV2(
                        @RequestParam(value = "name", required = true) String name,
                        @RequestParam(value = "type", required = true) String type) throws TextParseException {

                log.debug("V2 Resolving DNS query for domain: '{}' and type: '{}'", name, type);
                return ResponseEntity.ok(dnsService.resolveDns(name, type));
        }
}
