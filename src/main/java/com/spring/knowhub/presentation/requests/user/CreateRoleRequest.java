package com.spring.knowhub.presentation.requests.user;

import lombok.Data;

import java.util.Set;

@Data
public class CreateRoleRequest {
    private String name;
    private Set<Long> permissionIds;
}
