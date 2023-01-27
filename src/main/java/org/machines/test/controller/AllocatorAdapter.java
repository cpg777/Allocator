package org.machines.test.controller;

import org.machines.test.controller.dto.AllocatorRequest;
import org.machines.test.controller.dto.AllocatorResponse;
import org.machines.test.service.MachineService;
import org.machines.test.service.dto.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AllocatorAdapter implements AllocatorController {
    private static final Logger logger = LoggerFactory.getLogger(AllocatorAdapter.class);
    private final static String RESOURCE_NAME = "machine";

    @Autowired
    MachineService machineService;

    public AllocatorAdapter(MachineService machineService) {
        this.machineService = machineService;
    }
    @Override
    @GetMapping(path = "/" + RESOURCE_NAME, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<AllocatorResponse> allocatedResources(@RequestBody AllocatorRequest request) throws IOException {
        logger.info("Start request to allocate resources");
        List<Resource> costList = machineService.calculateCost(request);
        final List<AllocatorResponse> responses = new ArrayList<>();
        for (Resource resource: costList) {
            responses.add(AllocatorResponse.valueOf(resource));
        }
        logger.info("End request to allocate resources");
        return responses;
    }
}
