package com.spring.knowhub.domain.repositories.user;

import com.spring.knowhub.domain.models.user.Role;

import java.util.Optional;

public interface RoleCommandRepository {
    Optional<Role> save(Role role);
    void deleteById(Long id);
}
