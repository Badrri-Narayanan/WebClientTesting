package com.badrri.webclienttesting.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.badrri.webclienttesting.client.UserClient;
import com.badrri.webclienttesting.mapper.UserMapper;
import com.badrri.webclienttesting.model.User;
import com.badrri.webclienttesting.model.UserApiResponseEntity;
import java.util.List;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserClient userClient;

    @InjectMocks
    UserService userService;

    @Test
    void shouldGetUsers() {
        final List<UserApiResponseEntity> users = Instancio.createList(UserApiResponseEntity.class);
        when(userClient.getUsers()).thenReturn(users);
        final List<User> expectedResponse = UserMapper.convert(users);

        final var actualResponse = userService.getUsers();
        assertThat(actualResponse).isNotEmpty();
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }
}