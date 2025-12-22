package com.spring.knowhub.infrastructure.repositories.jpas.user;

import com.spring.knowhub.infrastructure.entities.user.PermissionEntity;
import com.spring.knowhub.infrastructure.entities.user.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface JpaRoleRepository extends JpaRepository<RoleEntity , Long> {
    Set<PermissionEntity> findByIdIn(Set<Long> ids);
}
