package com.spring.knowhub.domain.repositories.post;

import com.spring.knowhub.domain.models.post.Tag;

import java.util.Optional;

public interface TagCommandRepository {
    void deleteById(Long id);
    Optional<Tag> save(Tag tag);
}
