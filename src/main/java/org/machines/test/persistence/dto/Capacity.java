package org.machines.test.persistence.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class Capacity {
    private String type;
    private Integer units;

    public static Capacity valueOf(String type, Integer units) {
        return new Capacity(type, units);
    }
}
