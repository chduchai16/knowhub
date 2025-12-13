package com.spring.knowhub.domain.repositories.comment;

import com.spring.knowhub.domain.models.comment.CommentLike;

import java.util.Optional;

public interface CommentLikeRepository {
    Optional<CommentLike> save(CommentLike commentLike);
    void deleteById(Long id);
    Optional<CommentLike> findById(Long id);
    long countByCommentId(Long commentId);
}
