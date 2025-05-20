package com.badrri.webclienttesting.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.badrri.webclienttesting.model.User;
import com.badrri.webclienttesting.model.UserApiResponseEntity;
import com.badrri.webclienttesting.model.UserApiResponseEntity.Company;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class UserMapperTest {

    @Test
    void shouldReturnEmptyListWhenInputIsNull() {
        List<User> result = UserMapper.convert(null);
        assertThat(result).isEmpty();
    }

    @Test
    void shouldReturnEmptyListWhenInputIsEmpty() {
        List<User> result = UserMapper.convert(Collections.emptyList());
        assertThat(result).isEmpty();
    }

    @Test
    void shouldConvertUserApiResponseEntitiesToUsers() {
        UserApiResponseEntity entity1 = UserApiResponseEntity.builder()
            .name("John Doe")
            .email("john.doe@example.com")
            .phone("1234567890")
            .company(Company.builder().name("Company A").build())
            .build();

        UserApiResponseEntity entity2 = UserApiResponseEntity.builder()
            .name("Jane Smith")
            .email("jane.smith@example.com")
            .phone("0987654321")
            .company(Company.builder().name("Company B").build())
            .build();

        List<UserApiResponseEntity> entities = List.of(entity1, entity2);

        List<User> result = UserMapper.convert(entities);

        assertThat(result).hasSize(2);

        assertThat(result.get(0))
            .extracting(User::getName, User::getEmail, User::getPhone, User::getCompany)
            .containsExactly("John Doe", "john.doe@example.com", "1234567890", "Company A");

        assertThat(result.get(1))
            .extracting(User::getName, User::getEmail, User::getPhone, User::getCompany)
            .containsExactly("Jane Smith", "jane.smith@example.com", "0987654321", "Company B");
    }
}