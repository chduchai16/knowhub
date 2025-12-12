package com.spring.knowhub.domain.repositories.post;

import com.spring.knowhub.domain.models.post.PostLike;

import java.util.Optional;

public interface PostLikeCommandRepository {
    Optional<PostLike> save(PostLike postLike);
    void deleteById(Long id);
}
