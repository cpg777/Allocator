package org.machines.test.controller;

import org.machines.test.controller.dto.AllocatorRequest;
import org.machines.test.controller.dto.AllocatorResponse;

import java.io.IOException;
import java.util.List;

public interface AllocatorController {
    List<AllocatorResponse> allocatedResources(AllocatorRequest request) throws IOException;
}
