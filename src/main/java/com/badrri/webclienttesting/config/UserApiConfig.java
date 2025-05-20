package com.badrri.webclienttesting.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "user-api.client")
@Getter
@Setter
public class UserApiConfig {
    private String baseUrl;
}
