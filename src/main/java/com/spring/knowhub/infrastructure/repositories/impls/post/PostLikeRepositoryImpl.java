package com.spring.knowhub.infrastructure.repositories.impls.post;

import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.domain.models.post.PostLike;
import com.spring.knowhub.domain.repositories.post.PostLikeRepository;

import java.util.List;
import java.util.Optional;

public class PostLikeRepositoryImpl implements PostLikeRepository {
    @Override
    public Optional<PostLike> save(PostLike postLike) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Post> findPostLikesByPostId(Long postId) {
        return List.of();
    }
}
