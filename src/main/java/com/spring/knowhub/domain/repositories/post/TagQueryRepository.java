package com.spring.knowhub.domain.repositories.post;

import com.spring.knowhub.domain.models.post.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TagQueryRepository {
    Optional<Tag> findById(Long id);
    Page<Tag> findTagsPaged(Pageable pageable);
}
