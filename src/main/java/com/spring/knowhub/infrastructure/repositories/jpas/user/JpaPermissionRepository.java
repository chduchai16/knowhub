package com.spring.knowhub.infrastructure.repositories.jpas.user;

import com.spring.knowhub.infrastructure.entities.user.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface JpaPermissionRepository extends JpaRepository<PermissionEntity , Long> {
    Optional<PermissionEntity> findByCode(String code);
    Set<PermissionEntity> findByIdIn(Set<Long> ids);
    Boolean existsByCode(String code);
}
