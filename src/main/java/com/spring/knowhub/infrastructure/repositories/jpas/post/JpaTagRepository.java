package com.spring.knowhub.infrastructure.repositories.jpas.post;

import com.spring.knowhub.infrastructure.entities.post.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTagRepository extends JpaRepository<TagEntity , Long> {
}
