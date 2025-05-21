package com.badrri.webclienttesting.client;

import com.badrri.webclienttesting.config.UserApiConfig;
import com.badrri.webclienttesting.mapper.UserMapper;
import com.badrri.webclienttesting.model.User;
import com.badrri.webclienttesting.model.UserApiResponseEntity;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@Slf4j
public class UserClient {

    private final RestClient restClient;

    public UserClient(
        final UserApiConfig userApiConfig,
        final RestClient.Builder restClientBuilder
        ) {
        final String url = userApiConfig.getBaseUrl() + "/users";
        this.restClient = restClientBuilder
            .baseUrl(url)
            .build();
    }

    public List<UserApiResponseEntity> getUsers() {
        return restClient.get()
            .retrieve()
            .body(new ParameterizedTypeReference<>() {});
    }
}
