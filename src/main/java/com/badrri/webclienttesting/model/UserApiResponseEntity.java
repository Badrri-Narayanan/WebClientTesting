package com.badrri.webclienttesting.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserApiResponseEntity implements Serializable {
    private String name;
    private String email;
    private String phone;
    private Company company;

    @Data
    @Builder
    public static class Company implements Serializable {
        private String name;
        private String catchPhrase;
        private String bs;
    }
}
