package com.spring.knowhub.infrastructure.repositories.impls.user;

import com.spring.knowhub.domain.models.user.Role;
import com.spring.knowhub.domain.repositories.user.RoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class RoleRepositoryImpl implements RoleRepository {
    @Override
    public Optional<Role> save(Role role) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<Role> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<Role> findRolesPaged(Pageable pageable) {
        return null;
    }
}
