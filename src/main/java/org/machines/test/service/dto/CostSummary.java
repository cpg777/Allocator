package org.machines.test.service.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@ToString
public class CostSummary {
    private Machine machine;
    private Integer machineUnit;
    private Integer totalUnit;
    private Integer totalCost;

    public static CostSummary valueOf(final Machine machine, final Integer machineUnit, final Integer totalUnit, final Integer totalCost) {
        return new CostSummary(machine, machineUnit, totalUnit, totalCost);
    }
}
