package org.machines.test.controller.dto;

import lombok.*;
import org.machines.test.service.dto.Machine;
import org.machines.test.service.dto.Resource;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Getter
@ToString
public class AllocatorResponse {
    private String region;
    private String total_cost;
    private List<Machine> machines;

    public static AllocatorResponse valueOf(Resource resource) {
        return new AllocatorResponse(resource.getRegion(), resource.getTotal_cost(), resource.getMachines());
    }
}
