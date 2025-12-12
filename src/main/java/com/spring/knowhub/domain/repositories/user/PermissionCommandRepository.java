package com.spring.knowhub.domain.repositories.user;

import com.spring.knowhub.domain.models.user.Permission;

import java.util.Optional;

public interface PermissionCommandRepository {
    Optional<Permission> save(Permission permission);
    void deleteById(Long id);
}
