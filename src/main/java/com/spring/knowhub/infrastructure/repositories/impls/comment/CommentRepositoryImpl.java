package com.spring.knowhub.infrastructure.repositories.impls.comment;

import com.spring.knowhub.domain.models.comment.Comment;
import com.spring.knowhub.domain.repositories.comment.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class CommentRepositoryImpl implements CommentRepository {
    @Override
    public Optional<Comment> save(Comment comment) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<Comment> findCommentsPaged(Pageable pageable) {
        return null;
    }
}
