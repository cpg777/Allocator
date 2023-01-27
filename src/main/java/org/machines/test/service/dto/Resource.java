package org.machines.test.service.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class Resource {
    private String region;
    private String total_cost;
    private List<Machine> machines;

    private final static String EXCHANGE = "$";

    public static Resource valueOf(String region, String total, List<Machine> machines) {
        return new Resource(region, appendExchange(total), machines);
    }

    private static String appendExchange(String total) {
        return EXCHANGE + total;
    }
}
