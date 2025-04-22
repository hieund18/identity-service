package com.example.identity_service.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import com.example.identity_service.dto.request.UserCreationRequest;
import com.example.identity_service.dto.response.RoleResponse;
import com.example.identity_service.dto.response.UserResponse;
import com.example.identity_service.entity.Role;
import com.example.identity_service.entity.User;
import com.example.identity_service.exception.AppException;
import com.example.identity_service.repository.RoleRepository;
import com.example.identity_service.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("/test.properties")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    private UserCreationRequest userCreationRequest;
    private UserResponse userResponse;
    private User user;
    private Role role;
    private LocalDate dob;

    @BeforeEach
    void initData() {
        dob = LocalDate.of(2000, 10, 20);

        userCreationRequest = UserCreationRequest.builder()
                .username("hoa1")
                .password("12345678")
                .firstName("Hoa")
                .lastName("Nguyen")
                .dob(dob)
                .build();

        userResponse = UserResponse.builder()
                .id("aa0afb91")
                .username("hoa1")
                .firstName("Hoa")
                .lastName("Nguyen")
                .dob(dob)
                .build();

        role = Role.builder().name("USER").description("User role").build();

        user = User.builder()
                .id("aa0afb91")
                .username("hoa1")
                .firstName("Hoa")
                .lastName("Nguyen")
                .dob(dob)
                .roles(Set.of(role))
                .build();
    }

    @Test
    void createUser_validRequest_success() {
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(roleRepository.findById(anyString())).thenReturn(Optional.of(role));
        when(userRepository.save(any())).thenReturn(user);

        // WHEN
        var response = userService.createUser(userCreationRequest);

        // THEN
        assertThat(response.getId()).isEqualTo("aa0afb91");
        assertThat(response.getUsername()).isEqualTo("hoa1");
        assertThat(response.getRoles()).extracting(RoleResponse::getName).containsExactlyInAnyOrder("USER");
    }

    @Test
    void createUser_userExisted_fail() {
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        // WHEN
        var exception = assertThrows(AppException.class, () -> userService.createUser(userCreationRequest));

        // THEN
        assertThat(exception.getErrorCode().getCode()).isEqualTo(1002);
        assertThat(exception.getMessage()).isEqualTo("User existed");
    }

    @Test
    @WithMockUser(username = "hoaaaa")
    void getMyInfo_validRequest_success() {
        // GIVEN
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        // WHEN
        var response = userService.getMyInfo();

        // THEN
        assertThat(response.getId()).isEqualTo("aa0afb91");
        assertThat(response.getUsername()).isEqualTo("hoa1");
    }

    @Test
    @WithMockUser("hoaaa")
    void getMyInfo_userNotFound_error() {
        // GIVEN
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        // WHEN
        var exception = assertThrows(AppException.class, () -> userService.getMyInfo());

        // THEN
        assertThat(exception.getErrorCode().getCode()).isEqualTo(1006);
    }
}
