package com.spring.knowhub.presentation.requests.user;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.Set;

@Data
public class CreateRoleRequest {
    private String name;
    @Nullable
    private Set<Long> permissionIds;
}
