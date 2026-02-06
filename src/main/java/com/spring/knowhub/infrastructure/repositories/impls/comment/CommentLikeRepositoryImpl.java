package com.spring.knowhub.infrastructure.repositories.impls.comment;

import com.spring.knowhub.domain.models.comment.CommentLike;
import com.spring.knowhub.domain.repositories.comment.CommentLikeRepository;

import java.util.Optional;

public class CommentLikeRepositoryImpl implements CommentLikeRepository {
    @Override
    public CommentLike save(CommentLike commentLike) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<CommentLike> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public long countByCommentId(Long commentId) {
        return 0;
    }
}
