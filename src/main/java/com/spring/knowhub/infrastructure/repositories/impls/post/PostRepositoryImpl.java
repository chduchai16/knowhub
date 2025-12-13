package com.spring.knowhub.infrastructure.repositories.impls.post;

import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.domain.repositories.post.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class PostRepositoryImpl implements PostRepository {
    @Override
    public Optional<Post> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<Post> findPostsPaged(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Post> save(Post post) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }
}
