package com.spring.knowhub.domain.repositories.post;

import com.spring.knowhub.domain.models.post.PostMedia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PostMediaRepository {
    void deleteById(Long id);
    Optional<PostMedia> save(PostMedia postMedia);
    Optional<PostMedia> findById(Long id);
    Page<PostMedia> findPostMediasPaged(Pageable pageable) ;
}
