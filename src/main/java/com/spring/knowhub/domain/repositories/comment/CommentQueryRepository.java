package com.spring.knowhub.domain.repositories.comment;

import com.spring.knowhub.domain.models.comment.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CommentQueryRepository {
    Optional<Comment> findById(Long id);
    Page<Comment> findCommentsPaged(Pageable pageable);
}
