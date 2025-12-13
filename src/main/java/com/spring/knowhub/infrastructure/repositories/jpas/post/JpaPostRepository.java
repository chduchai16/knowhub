package com.spring.knowhub.infrastructure.repositories.jpas.post;

import com.spring.knowhub.infrastructure.entities.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPostRepository extends JpaRepository<PostEntity , Long> {
}
