package com.example.identity_service.service;

import com.example.identity_service.dto.request.RoleRequest;
import com.example.identity_service.dto.response.RoleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {
    RoleResponse createRole(RoleRequest request);

    Page<RoleResponse> getRoles(Pageable pageable);

    void deleteRole(String role);
}
