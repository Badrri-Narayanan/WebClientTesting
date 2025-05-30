package com.badrri.webclienttesting.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.badrri.webclienttesting.model.User;
import com.badrri.webclienttesting.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @Test
    void shouldRetrieveUsers() {
        List<User> users = Instancio.createList(User.class);
        when(userService.getUsers()).thenReturn(users);

        final var actualResponse = userController.getUsers();

        assertThat(actualResponse).isNotEmpty();
        assertThat(actualResponse).isEqualTo(users);
    }
}