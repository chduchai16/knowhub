package com.spring.knowhub.domain.repositories.post;

import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.domain.specifications.Specification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PostRepository {
    Optional<Post> findById(Long id);
    Page<Post> findPostsPaged(Specification<Post> specification ,Pageable pageable) ;
    Optional<Post> save(Post post);
    void deleteById(Long id);
}
