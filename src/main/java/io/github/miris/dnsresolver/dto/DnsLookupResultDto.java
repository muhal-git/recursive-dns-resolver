package io.github.miris.dnsresolver.dto;

import java.util.List;

import org.xbill.DNS.Record;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DnsLookupResultDto {

    private String name;
    private int type;
    private long ttl;
    private String data;

    public DnsLookupResultDto(Record dnsRecord) {
        this.name = dnsRecord.getName().toString();
        this.type = dnsRecord.getType();
        this.ttl = dnsRecord.getTTL();
        this.data = dnsRecord.rdataToString();
    }

    public static List<DnsLookupResultDto> fromDnsRecords(Record[] dnsRecords) {
        return List.of(dnsRecords).stream().map(DnsLookupResultDto::new).toList();
    }

}
