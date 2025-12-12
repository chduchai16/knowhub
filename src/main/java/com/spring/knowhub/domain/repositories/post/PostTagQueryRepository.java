package com.spring.knowhub.domain.repositories.post;

import com.spring.knowhub.domain.models.post.PostTag;

import java.util.List;
import java.util.Optional;

public interface PostTagQueryRepository {
    Optional<PostTag> findById(Long id);
    List<PostTag> findTagsByPostId(Long postId);
}
