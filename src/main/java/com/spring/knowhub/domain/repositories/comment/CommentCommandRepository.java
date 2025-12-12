package com.spring.knowhub.domain.repositories.comment;

import com.spring.knowhub.domain.models.comment.Comment;

import java.util.Optional;

public interface CommentCommandRepository {
    Optional<Comment> save(Comment comment);
    void deleteById(Long id);
}
