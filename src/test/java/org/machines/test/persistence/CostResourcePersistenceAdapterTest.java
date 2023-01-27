package org.machines.test.persistence;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.machines.test.persistence.dto.Capacity;
import org.machines.test.persistence.dto.RegionCost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
public class CostResourcePersistenceAdapterTest {

    @TestConfiguration
    static class CostResourcePersistenceAdapterTestContextConfiguration {
        @Bean
        public CostResourcePersistenceAdapter costResourcePersistenceAdapter() {
           return new CostResourcePersistenceAdapter();
        }
    }
    @Autowired
    CostResourcePersistencePort costResourcePersistencePort;

    @Autowired
    CostResourcePersistenceAdapter costResourcePersistenceAdapter;

    @Before
    public void setUp() {
        costResourcePersistenceAdapter.costFile = "cost.json";
        costResourcePersistenceAdapter.capacitiesFile = "capacities.json";
    }

    @Test
    public void getCapacities() throws IOException {
        List<Capacity> capacityList = costResourcePersistencePort.getCapacities();
        Assert.assertEquals(6, capacityList.size());
    }

    @Test
    public void getRegionCost() throws IOException {
        List<RegionCost> regionCosts = costResourcePersistencePort.getRegionCost();
        Assert.assertEquals(3, regionCosts.size());
    }
}
