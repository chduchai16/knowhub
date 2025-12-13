package com.spring.knowhub.infrastructure.repositories.impls.post;

import com.spring.knowhub.domain.models.post.PostMedia;
import com.spring.knowhub.domain.repositories.post.PostMediaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class PostMediaRepositoryImpl implements PostMediaRepository {
    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<PostMedia> save(PostMedia postMedia) {
        return Optional.empty();
    }

    @Override
    public Optional<PostMedia> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<PostMedia> findPostMediasPaged(Pageable pageable) {
        return null;
    }
}
