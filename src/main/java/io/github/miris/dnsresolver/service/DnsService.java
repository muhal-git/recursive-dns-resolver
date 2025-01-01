package io.github.miris.dnsresolver.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

import io.github.miris.dnsresolver.dto.DnsLookupResultDto;
import lombok.extern.slf4j.Slf4j;

/**
 * Service for DNS resolution.
 */
@Service
@Slf4j
public class DnsService {
    
    public List<DnsLookupResultDto> resolveDns(String domain, String type) throws TextParseException {
            
            // Perform a Lookup for the domain and record type
            Lookup lookup = new Lookup(domain, Type.value(type));
            lookup.run();

            // Check the results
            if (lookup.getResult() == Lookup.SUCCESSFUL) {

                Record[] dnsRecords = lookup.getAnswers();
                log.debug("Found {} '{}' DNS records for domain '{}'", dnsRecords.length, type, domain);
                for (Record dnsRecord : dnsRecords) {
                    log.debug("Found DNS record: {}", dnsRecord);
                }

                // Convert the records to DTOs
                return DnsLookupResultDto.fromDnsRecords(dnsRecords);

            } else {
                log.error("Lookup failed: {}", lookup.getErrorString());
                throw new TextParseException("Lookup failed: " + lookup.getErrorString());
            }

    }

}