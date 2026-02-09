package com.spring.knowhub.infrastructure.repositories.jpas.comment;

import com.spring.knowhub.infrastructure.entities.comment.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {
    @Query("SELECT c FROM CommentEntity c WHERE c.post.id = :postId")
    Page<CommentEntity> findByPostId(@Param("postId") Long postId, Pageable pageable);

    @Query("SELECT COUNT(c) FROM CommentEntity c WHERE c.post.id = :postId")
    Long countByPostId(@Param("postId") Long postId);
}
