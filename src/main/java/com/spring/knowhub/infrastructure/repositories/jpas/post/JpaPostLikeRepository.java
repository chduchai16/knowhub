package com.spring.knowhub.infrastructure.repositories.jpas.post;

import com.spring.knowhub.infrastructure.entities.post.PostLikeEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPostLikeRepository extends JpaRepository<PostLikeEntity, Long> {
    List<PostLikeEntity> findByPostId(Long postId);
    Long countByPostId(Long postId);
    Optional<PostLikeEntity> findByPostIdAndUserId(Long postId, Long userId);
}
