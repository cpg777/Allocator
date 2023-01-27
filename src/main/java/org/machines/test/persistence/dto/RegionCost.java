package org.machines.test.persistence.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class RegionCost {
    private String region;
    private List<ResourceCost> resources;

    public static RegionCost valueOf(String region, List<ResourceCost> resources) {
        return new RegionCost(region, resources);
    }
}
