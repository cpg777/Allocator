package org.machines.test.persistence;

import org.machines.test.persistence.dto.Capacity;
import org.machines.test.persistence.dto.RegionCost;

import java.io.IOException;
import java.util.List;

public interface CostResourcePersistencePort {
    List<Capacity> getCapacities() throws IOException;
    List<RegionCost> getRegionCost() throws IOException;
}
