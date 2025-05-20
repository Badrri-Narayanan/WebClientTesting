package com.badrri.webclienttesting.mapper;

import static java.util.Objects.isNull;

import com.badrri.webclienttesting.model.User;
import com.badrri.webclienttesting.model.UserApiResponseEntity;
import java.util.Collections;
import java.util.List;

public class UserMapper {

    private UserMapper() {}

    public static List<User> convert(final List<UserApiResponseEntity> userApiResponseEntities) {
        if(isNull(userApiResponseEntities))
            return Collections.emptyList();

        return userApiResponseEntities.stream()
            .map(userApiResponseEntity -> User.builder()
                .name(userApiResponseEntity.getName())
                .email(userApiResponseEntity.getEmail())
                .phone(userApiResponseEntity.getPhone())
                .company(userApiResponseEntity.getCompany().getName())
                .build())
            .toList();
    }
}
