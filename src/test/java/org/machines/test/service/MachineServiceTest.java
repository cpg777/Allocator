package org.machines.test.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.machines.test.controller.dto.AllocatorRequest;
import org.machines.test.persistence.CostResourcePersistencePort;
import org.machines.test.persistence.dto.Capacity;
import org.machines.test.persistence.dto.RegionCost;
import org.machines.test.persistence.dto.ResourceCost;
import org.machines.test.service.dto.Resource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class MachineServiceTest {

    private static final List<ResourceCost> resourceCostNy = new ArrayList<>();
    private static final List<ResourceCost> resourceCostIn = new ArrayList<>();
    private static final List<ResourceCost> resourceCostCh = new ArrayList<>();
    private static final List<RegionCost> regionCost = new ArrayList<>();

    private static final List<Capacity> capacities = new ArrayList<>();

    @TestConfiguration
    static class MachineServiceTestContextConfiguration {
        @Bean
        public MachineService machineService() {
            return new MachineService();
        }
    }

    @MockBean
    CostResourcePersistencePort costResourcePersistencePort;

    @Autowired
    MachineService machineService;

    @Before
    public void setUp() throws Exception {
        resourceCostNy.add(ResourceCost.valueOf("10XLARGE", 2820));
        resourceCostNy.add(ResourceCost.valueOf("8XLARGE", 1400));
        resourceCostNy.add(ResourceCost.valueOf("4XLARGE", 774));
        resourceCostNy.add(ResourceCost.valueOf("2XLARGE", 450));
        resourceCostNy.add(ResourceCost.valueOf("XLARGE", 230));
        resourceCostNy.add(ResourceCost.valueOf("LARGE", 120));

        resourceCostIn.add(ResourceCost.valueOf("10XLARGE", 2970));
        resourceCostIn.add(ResourceCost.valueOf("8XLARGE", 1300));
        resourceCostIn.add(ResourceCost.valueOf("4XLARGE", 890));
        resourceCostIn.add(ResourceCost.valueOf("2XLARGE", 413));
        resourceCostIn.add(ResourceCost.valueOf("LARGE", 140));

        resourceCostCh.add(ResourceCost.valueOf("8XLARGE", 1180));
        resourceCostCh.add(ResourceCost.valueOf("4XLARGE", 670));
        resourceCostCh.add(ResourceCost.valueOf("XLARGE", 200));
        resourceCostCh.add(ResourceCost.valueOf("LARGE", 110));

        regionCost.add(RegionCost.valueOf("NEW_YORK", resourceCostNy));
        regionCost.add(RegionCost.valueOf("INDIA", resourceCostIn));
        regionCost.add(RegionCost.valueOf("CHINA", resourceCostCh));

        capacities.add(Capacity.valueOf("LARGE", 10));
        capacities.add(Capacity.valueOf("XLARGE", 20));
        capacities.add(Capacity.valueOf("2XLARGE", 40));
        capacities.add(Capacity.valueOf("4XLARGE", 80));
        capacities.add(Capacity.valueOf("8XLARGE", 160));
        capacities.add(Capacity.valueOf("10XLARGE", 320));

        Mockito.when(costResourcePersistencePort.getRegionCost()).thenReturn(regionCost);
        Mockito.when(costResourcePersistencePort.getCapacities()).thenReturn(capacities);
    }

    @Test
    public void calculateCost() throws IOException {
        final AllocatorRequest request = AllocatorRequest.valueOf(1, 1150);
        List<Resource> resources = machineService.calculateCost(request);
        Assert.assertEquals(3, resources.size());
    }
}
