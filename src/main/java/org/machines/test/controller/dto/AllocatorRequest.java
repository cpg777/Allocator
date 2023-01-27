package org.machines.test.controller.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@ToString
public class AllocatorRequest {
    @NotNull(message = "hours should be provided")
    private Integer hours;
    @NotNull(message = "capacity should be provided")
    private Integer capacity;

    public static AllocatorRequest valueOf(Integer hours, Integer capacity) {
        return new AllocatorRequest(hours, capacity);
    }
}
