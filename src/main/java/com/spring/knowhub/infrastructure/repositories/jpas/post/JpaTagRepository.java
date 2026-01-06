package com.spring.knowhub.infrastructure.repositories.jpas.post;

import com.spring.knowhub.domain.models.post.Tag;
import com.spring.knowhub.infrastructure.entities.post.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaTagRepository extends JpaRepository<TagEntity , Long> {
    Optional<TagEntity> findByName(String name);
    Boolean existsByName(String name);
}
