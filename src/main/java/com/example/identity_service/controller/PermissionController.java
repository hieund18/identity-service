package com.example.identity_service.controller;

import com.example.identity_service.dto.request.PermissionRequest;
import com.example.identity_service.dto.response.ApiResponse;
import com.example.identity_service.dto.response.PermissionResponse;
import com.example.identity_service.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.createPermission(request))
                .build();
    }

    @GetMapping
    ApiResponse<Page<PermissionResponse>> getPermissions(
            @PageableDefault(sort = "name", direction = Sort.Direction.DESC) Pageable pageable) {
        return ApiResponse.<Page<PermissionResponse>>builder()
                .result(permissionService.getPermissions(pageable))
                .build();
    }

    @DeleteMapping("/{permission}")
    ApiResponse<Void> deletePermission(@PathVariable String permission) {
        permissionService.deletePermission(permission);
        return ApiResponse.<Void>builder().build();
    }
}
