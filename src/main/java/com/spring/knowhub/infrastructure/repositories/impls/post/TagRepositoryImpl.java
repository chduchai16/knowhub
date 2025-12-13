package com.spring.knowhub.infrastructure.repositories.impls.post;

import com.spring.knowhub.domain.models.post.Tag;
import com.spring.knowhub.domain.repositories.post.TagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class TagRepositoryImpl implements TagRepository {
    @Override
    public Optional<Tag> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<Tag> findTagsPaged(Pageable pageable) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<Tag> save(Tag tag) {
        return Optional.empty();
    }
}
