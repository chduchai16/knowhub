package com.spring.knowhub.infrastructure.repositories.jpas.post;

import com.spring.knowhub.infrastructure.entities.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaPostRepository extends JpaRepository<PostEntity, Long>, JpaSpecificationExecutor<PostEntity> {

    @Query("SELECT DISTINCT p FROM PostEntity p " +
            "LEFT JOIN FETCH p.postTags pt " +
            "LEFT JOIN FETCH pt.tag " +
            "LEFT JOIN FETCH p.user " +
            "WHERE p.id = :id")
    Optional<PostEntity> findByIdWithTags(@Param("id") Long id);
}
