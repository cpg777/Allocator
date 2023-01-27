package org.machines.test.persistence.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class ResourceCost {
    private String type;
    private Integer cost;

    public static ResourceCost valueOf(String type, Integer cost) {
        return new ResourceCost(type, cost);
    }
}
