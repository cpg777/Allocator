package org.machines.test.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.machines.test.persistence.dto.Capacity;
import org.machines.test.persistence.dto.RegionCost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Repository
public class CostResourcePersistenceAdapter implements CostResourcePersistencePort {

    private static final Logger logger = LoggerFactory.getLogger(CostResourcePersistenceAdapter.class);
    @Value("${data.capacities}")
    String capacitiesFile;
    @Value("${data.cost}")
    String costFile;
    @Override
    public List<Capacity> getCapacities() throws IOException {
        logger.info("Start persistence get capacities information");
        final List<Capacity> capacityList = capacities();
        if (capacityList.size() == 0) {
            throw new IOException("Unable to read capacities.json file");
        }
        logger.info("End persistence get capacities information");
        return capacityList;
    }

    @Override
    public List<RegionCost> getRegionCost() throws IOException {
        logger.info("Start persistence get region´s cost");
        final List<RegionCost> regionCostList = regionCosts();
        if (regionCostList.size() == 0) {
            throw new IOException("Unable to read cost.json file");
        }
        logger.info("End persistence get region´s cost");
        return regionCostList;
    }

    private List<Capacity> capacities() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final File file = new File(
                this.getClass().getClassLoader().getResource(capacitiesFile).getFile()
        );
        return Arrays.asList(objectMapper.readValue(file,  Capacity[].class));
    }

    private List<RegionCost> regionCosts () throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final File file = new File(
                this.getClass().getClassLoader().getResource(costFile).getFile()
        );
        return Arrays.asList(objectMapper.readValue(file, RegionCost[].class));
    }
}
