package org.machines.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.machines.test.controller.dto.AllocatorRequest;
import org.machines.test.utils.SpringContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AllocatorAdapter.class)
public class AllocatorAdapterTest {
    private final static String RESOURCE_NAME = "machine";

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @MockBean
    AllocatorController allocatorController;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @TestConfiguration
    static class AllocatorAdapterTestContextConfiguration {
        @Bean
        public SpringContext context() {
            return new SpringContext();
        }
    }

    @Test
    public void allocatedResources() throws Exception {
        AllocatorRequest allocatorRequest = AllocatorRequest.valueOf(1, 1);
        mvc.perform(get("/" + RESOURCE_NAME)
                        .with(csrf())
                        .content(new ObjectMapper().writeValueAsString(allocatorRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().isNotFound());
    }
}
