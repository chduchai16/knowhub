package com.spring.knowhub.infrastructure.repositories.jpas.user;

import com.spring.knowhub.infrastructure.entities.user.PermissionEntity;
import com.spring.knowhub.infrastructure.entities.user.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface JpaRoleRepository extends JpaRepository<RoleEntity , Long> {
    Set<PermissionEntity> findByIdIn(Set<Long> ids);

    @Query("""
        SELECT r FROM RoleEntity r
        WHERE (:keyword IS NULL
            OR LOWER(r.name) LIKE LOWER(CONCAT('%', :keyword, '%')))
    """)
    Page<RoleEntity> search(@Param("keyword") String keyword, Pageable pageable);
    Boolean existsByName(String name);
}
