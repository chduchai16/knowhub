package com.spring.knowhub.domain.repositories.post;

import com.spring.knowhub.domain.models.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PostRepository {
    Optional<Post> findById(Long id);
    Page<Post> findPostsPaged(Pageable pageable) ;
    Optional<Post> save(Post post);
    void deleteById(Long id);
}
