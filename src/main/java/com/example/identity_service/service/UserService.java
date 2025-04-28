package com.example.identity_service.service;

import com.example.identity_service.dto.request.PasswordCreationRequest;
import com.example.identity_service.dto.request.UserCreationRequest;
import com.example.identity_service.dto.request.UserUpdateRequest;
import com.example.identity_service.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserResponse createUser(UserCreationRequest request);

    void createPassword(PasswordCreationRequest request);

    Page<UserResponse> getUsers(Pageable pageable);

    UserResponse getUser(String userId);

    UserResponse getMyInfo();

    UserResponse updateUser(String userId, UserUpdateRequest request);

    void deleteUser(String userId);
}
