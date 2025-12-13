package com.spring.knowhub.infrastructure.repositories.impls.user;

import com.spring.knowhub.domain.models.user.Permission;
import com.spring.knowhub.domain.repositories.user.PermissionRepository;

import java.util.List;
import java.util.Optional;

public class PermissionRepositoryImpl implements PermissionRepository {
    @Override
    public Optional<Permission> save(Permission permission) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<Permission> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Permission> findAll() {
        return List.of();
    }
}
