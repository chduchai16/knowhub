package com.spring.knowhub.domain.repositories.post;

import com.spring.knowhub.domain.models.post.PostTag;

import java.util.Optional;

public interface PostTagCommandRepository {
    Optional<PostTag> save(PostTag postTag);
    void deleteById(Long id);
}
