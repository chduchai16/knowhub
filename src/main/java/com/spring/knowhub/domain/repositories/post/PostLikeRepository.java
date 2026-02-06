package com.spring.knowhub.domain.repositories.post;

import com.spring.knowhub.domain.models.post.PostLike;

import java.util.List;
import java.util.Optional;

public interface PostLikeRepository {
    Optional<PostLike> save(PostLike postLike);

    void deleteById(Long id);

    List<PostLike> findPostLikesByPostId(Long postId);

    Long countByPostId(Long postId);

    Optional<PostLike> findByPostIdAndUserId(Long postId, Long userId);
}
