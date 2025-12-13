package com.spring.knowhub.domain.repositories.user;

import com.spring.knowhub.domain.models.user.Permission;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository {
    Optional<Permission> save(Permission permission);
    void deleteById(Long id);
    Optional<Permission> findById(Long id);
    List<Permission> findAll();
}
