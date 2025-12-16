package com.spring.knowhub.infrastructure.repositories.jpas.comment;

import com.spring.knowhub.infrastructure.entities.comment.CommentLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCommentLikeRepository extends JpaRepository<CommentLikeEntity , Long> {
}
