package org.machines.test.service;

import org.machines.test.controller.dto.AllocatorRequest;
import org.machines.test.persistence.CostResourcePersistencePort;
import org.machines.test.persistence.dto.Capacity;
import org.machines.test.persistence.dto.RegionCost;
import org.machines.test.persistence.dto.ResourceCost;
import org.machines.test.service.dto.CostSummary;
import org.machines.test.service.dto.Machine;
import org.machines.test.service.dto.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MachineService {
    private static final Logger logger = LoggerFactory.getLogger(MachineService.class);

    @Autowired
    CostResourcePersistencePort costResourcePersistencePort;

    public List<Resource> calculateCost(@Validated AllocatorRequest allocatorRequest) throws IOException {
        logger.info("Start processing resource´s cost");
        final List<Capacity> capacities = costResourcePersistencePort.getCapacities();
        final List<RegionCost> costs = costResourcePersistencePort.getRegionCost();
        final List<Resource> resources = new ArrayList<>();
        for (RegionCost rc: costs) {
            List<CostSummary> costSummaryList = new ArrayList<>();
            List<Machine> machineList = new ArrayList<>();
            Integer totalCost = 0;
            Integer capacityUnits = allocatorRequest.getCapacity();
            do {
                for (ResourceCost c: rc.getResources()) {
                    Capacity capacity = capacities.stream().filter(ca -> ca.getType().equals(c.getType())).findFirst().get();
                    if (capacityUnits >= capacity.getUnits()) {
                        Integer numberOfResources = (int) Math.round((double) capacityUnits / (double) capacity.getUnits());
                        Integer quantityInHour = (int) Math.round((double) numberOfResources / (double) allocatorRequest.getHours());
                        Integer machineCost = quantityInHour * c.getCost();
                        costSummaryList.add(CostSummary.valueOf(Machine.valueOf(c.getType(), quantityInHour), capacity.getUnits(), quantityInHour * capacity.getUnits(),machineCost));
                    }
                }
                CostSummary costSummary = costSummaryList.stream().min(Comparator.comparingInt(CostSummary::getTotalCost)).get();
                machineList.add(costSummary.getMachine());
                totalCost = totalCost + costSummary.getTotalCost();
                capacityUnits = capacityUnits - costSummary.getTotalUnit();
            } while (capacityUnits > 0);
            resources.add(Resource.valueOf(rc.getRegion(), String.valueOf(totalCost), machineList));
        }
        logger.info("End processing resource´s cost");
        return resources;
    }
}
