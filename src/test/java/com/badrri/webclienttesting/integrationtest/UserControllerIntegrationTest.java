package com.badrri.webclienttesting.integrationtest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.tomakehurst.wiremock.client.WireMock;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.wiremock.spring.EnableWireMock;

@SpringBootTest
@AutoConfigureMockMvc
@EnableWireMock
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    void getHelloWorld() throws Exception {
        String expected = "Hello World";

        mockMvc.perform(get("/hello-world"))
            .andExpect(status().isOk())
            .andExpect(content().string(expected));
    }

    @Test
    void getUser() throws Exception {
        final Resource externalApiResource = resourceLoader.getResource("classpath:user-api-response.json");
        final Resource expectedApiResource = resourceLoader.getResource("classpath:expected-users-response.json");

        final String apiResponse = new String(externalApiResource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        final String expected = new String(expectedApiResource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        WireMock.stubFor(WireMock.get("/users").willReturn(WireMock.ok(apiResponse)));

        mockMvc.perform(get("/users"))
            .andExpect(status().isOk())
            .andExpect(content().string(expected));
    }

}