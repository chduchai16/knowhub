package com.spring.knowhub.domain.repositories.post;

import com.spring.knowhub.domain.models.post.PostMedia;

import java.util.Optional;

public interface PostMediaCommandRepository {
    void deleteById(Long id);
    Optional<PostMedia> save(PostMedia postMedia);
}
