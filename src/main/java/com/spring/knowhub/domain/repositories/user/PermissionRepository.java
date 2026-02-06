package com.spring.knowhub.domain.repositories.user;

import com.spring.knowhub.domain.models.user.Permission;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PermissionRepository {
    Permission save(Permission permission);
    void deleteById(Long id);
    Optional<Permission> findById(Long id);
    Set<Permission> findByIds(Set<Long> ids);
    Optional<Permission> findByCode(String code);
    List<Permission> findAll();
    Boolean existsByCode(String code);
}
