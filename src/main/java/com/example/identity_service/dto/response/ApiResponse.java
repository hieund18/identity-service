package com.example.identity_service.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ApiResponse<T> implements Serializable {
    @Builder.Default
    int code = 1000;

    String message;
    T result;
}
