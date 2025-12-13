package com.spring.knowhub.infrastructure.repositories.jpas.user;

import com.spring.knowhub.infrastructure.entities.user.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPermissionRepository extends JpaRepository<PermissionEntity , Long> {
}
