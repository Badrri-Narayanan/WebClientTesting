package com.badrri.webclienttesting.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.badrri.webclienttesting.client.UserClient;
import com.badrri.webclienttesting.model.User;
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
        List<User> users = Instancio.createList(User.class);
        when(userClient.getUsers()).thenReturn(users);

        final var actualResponse = userService.getUsers();
        assertThat(actualResponse).isNotEmpty();
        assertThat(actualResponse).isEqualTo(users);
    }
}