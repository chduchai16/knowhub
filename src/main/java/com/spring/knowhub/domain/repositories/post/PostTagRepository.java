package com.spring.knowhub.domain.repositories.post;

import com.spring.knowhub.domain.models.post.PostTag;

import java.util.List;
import java.util.Optional;

public interface PostTagRepository {
    PostTag save(PostTag postTag);

    void deleteById(Long id);

    Optional<PostTag> findById(Long id);

    List<PostTag> findTagsByPostId(Long postId);
}
