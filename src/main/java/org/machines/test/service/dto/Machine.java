package org.machines.test.service.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@ToString
public class Machine {
    private String type;
    private Integer quantity;

    public static Machine valueOf(final String type, final Integer quantity) {
        return new Machine(type, quantity);
    }
}
