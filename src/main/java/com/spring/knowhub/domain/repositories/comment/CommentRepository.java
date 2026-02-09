package com.spring.knowhub.domain.repositories.comment;

import com.spring.knowhub.domain.models.comment.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);
    void deleteById(Long id);
    Optional<Comment> findById(Long id);
    Page<Comment> findCommentsPaged(Pageable pageable);
    Page<Comment> findByPostIdPaged(Long postId, Pageable pageable);
    Page<Comment> findByRootIdPaged(Long rootId, Pageable pageable);
    Long countByPostId(Long postId);
}