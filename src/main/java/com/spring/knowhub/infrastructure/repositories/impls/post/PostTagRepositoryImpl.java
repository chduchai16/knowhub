package com.spring.knowhub.infrastructure.repositories.impls.post;

import com.spring.knowhub.domain.models.post.PostTag;
import com.spring.knowhub.domain.repositories.post.PostTagRepository;

import java.util.List;
import java.util.Optional;

public class PostTagRepositoryImpl implements PostTagRepository {
    @Override
    public Optional<PostTag> save(PostTag postTag) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<PostTag> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<PostTag> findTagsByPostId(Long postId) {
        return List.of();
    }
}
