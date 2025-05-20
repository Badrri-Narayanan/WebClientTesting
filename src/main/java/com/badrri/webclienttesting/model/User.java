package com.badrri.webclienttesting.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String name;
    private String email;
    private String phone;
    private String company;
}
