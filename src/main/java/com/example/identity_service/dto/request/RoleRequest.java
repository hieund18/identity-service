package com.example.identity_service.dto.request;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRequest {
    @NotBlank(message = "ROLE_CANNOT_BLANK")
    String name;

    String description;

    Set<String> permissions;
}
