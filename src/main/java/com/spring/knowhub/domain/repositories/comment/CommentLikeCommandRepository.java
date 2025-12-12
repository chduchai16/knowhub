package com.spring.knowhub.domain.repositories.comment;

import com.spring.knowhub.domain.models.comment.CommentLike;

import java.util.Optional;

public interface CommentLikeCommandRepository {
    Optional<CommentLike> save(CommentLike commentLike);
    void deleteById(Long id);
}
