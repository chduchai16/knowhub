package com.spring.knowhub.domain.repositories.user;

import com.spring.knowhub.domain.models.user.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RoleQueryRepository {
    Optional<Role> findById(Long id);
    Page<Role> findRolesPaged(Pageable pageable);
}
