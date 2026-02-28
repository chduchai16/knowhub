package com.spring.knowhub.infrastructure.repositories.jpas.user;

import com.spring.knowhub.infrastructure.entities.user.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    @EntityGraph(attributePaths = {"role", "role.permissions"})
    Optional<UserEntity> findByUsername(String username);

    @EntityGraph(attributePaths = {"role", "role.permissions"})
    Optional<UserEntity> findByEmail(String email);

    @EntityGraph(attributePaths = {"role", "role.permissions"})
    Optional<UserEntity> findFirstByEmail(String email);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
