package io.github.miris.dnsresolver.service;

import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

/**
 * Service for DNS resolution.
 */
@Service
@Slf4j
public class DnsService {
    
    public Record[] resolveDns(String domain, String type) throws TextParseException {
            
            // Perform a Lookup for the domain and record type
            Lookup lookup = new Lookup(domain, Type.value(type));
            lookup.run();

            // Check the results
            if (lookup.getResult() == Lookup.SUCCESSFUL) {

                Record[] dnsRecords = lookup.getAnswers();
                for (Record dnsRecord : dnsRecords) {
                    log.debug("Found DNS record: {}", dnsRecord);
                }

                return lookup.getAnswers();

            } else {
                log.error("Lookup failed: {}", lookup.getErrorString());
                throw new TextParseException("Lookup failed: " + lookup.getErrorString());
            }

    }

}