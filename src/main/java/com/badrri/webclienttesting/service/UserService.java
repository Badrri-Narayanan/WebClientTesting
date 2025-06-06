package com.badrri.webclienttesting.service;

import com.badrri.webclienttesting.client.UserClient;
import com.badrri.webclienttesting.mapper.UserMapper;
import com.badrri.webclienttesting.model.User;
import com.badrri.webclienttesting.model.UserApiResponseEntity;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserClient userClient;

    public List<User> getUsers() {
        final List<UserApiResponseEntity> userApiResponseEntities = userClient.getUsers();
        return UserMapper.convert(userApiResponseEntities);
    }
}
