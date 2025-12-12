package com.spring.knowhub.domain.repositories.post;

import com.spring.knowhub.domain.models.post.Post;

import java.util.Optional;

public interface PostCommandRepository {
    Optional<Post> save(Post post);
    void deleteById(Long id);
}
