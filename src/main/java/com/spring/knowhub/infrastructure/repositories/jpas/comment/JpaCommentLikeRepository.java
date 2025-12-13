package com.spring.knowhub.infrastructure.repositories.jpas.comment;

import com.spring.knowhub.domain.models.comment.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCommentLikeRepository extends JpaRepository<CommentLike , Long> {
}
