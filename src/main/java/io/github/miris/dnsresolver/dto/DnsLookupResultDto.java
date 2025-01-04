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

    public static DnsLookupResultDto of(Record dnsRecord) {
        DnsLookupResultDto dto = new DnsLookupResultDto();
        dto.setName(dnsRecord.getName().toString());
        dto.setType(dnsRecord.getType());
        dto.setTtl(dnsRecord.getTTL());
        dto.setData(dnsRecord.rdataToString());
        return dto;
    }

    public static List<DnsLookupResultDto> fromDnsRecords(Record[] dnsRecords) {
        return List.of(dnsRecords).stream().map(DnsLookupResultDto::of).toList();
    }

}
