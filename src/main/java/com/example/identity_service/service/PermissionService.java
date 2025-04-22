package com.example.identity_service.service;

import com.example.identity_service.dto.request.PermissionRequest;
import com.example.identity_service.dto.response.PermissionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PermissionService {
    PermissionResponse createPermission(PermissionRequest request);

    Page<PermissionResponse> getPermissions(Pageable pageable);

    void deletePermission(String permission);
}
