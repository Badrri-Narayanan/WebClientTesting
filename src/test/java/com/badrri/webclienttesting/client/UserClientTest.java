package com.badrri.webclienttesting.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import com.badrri.webclienttesting.config.UserApiConfig;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.client.MockRestServiceServer;

@RestClientTest(value = {UserClient.class, UserApiConfig.class})
class UserClientTest {

    @Autowired
    private UserClient userClient;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ResourceLoader resourceLoader;

    @BeforeEach
    void setUp() throws Exception {
        final Resource resource = resourceLoader.getResource("classpath:user-api-response.json");

        final String apiResponse = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        server.expect(requestTo("https://jsonplaceholder.typicode.com/users"))
            .andRespond(withSuccess(apiResponse, MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldGetUsers() {
        final var users = userClient.getUsers();

        assertThat(users).isNotEmpty();
    }
}