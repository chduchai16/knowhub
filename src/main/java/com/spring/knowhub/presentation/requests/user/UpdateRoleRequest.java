package com.spring.knowhub.presentation.requests.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
@Data
public class UpdateRoleRequest {
    private Long id ;
    private String name;
    private Set<Long> permissionIds;
}
