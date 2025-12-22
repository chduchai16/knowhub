package com.spring.knowhub.presentation.requests.user;

import java.util.Set;

public class UpdateRoleRequest {
    private Long id ;
    private String name;
    private Set<Long> permissionIds;
}
