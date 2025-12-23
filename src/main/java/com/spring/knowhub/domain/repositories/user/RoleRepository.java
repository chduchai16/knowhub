package com.spring.knowhub.domain.repositories.user;

import com.spring.knowhub.domain.models.user.Permission;
import com.spring.knowhub.domain.models.user.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.Set;

public interface RoleRepository {
    Optional<Role> save(Role role);
    Void deleteById(Long id);
    Optional<Role> findById(Long id);
    Page<Role> findRolesPaged(Pageable pageable , String keyword);
    Boolean existsByName(String name);
    Boolean existsById(Long id);
}
