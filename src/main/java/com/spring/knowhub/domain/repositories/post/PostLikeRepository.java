package com.spring.knowhub.domain.repositories.post;

import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.domain.models.post.PostLike;

import java.util.List;
import java.util.Optional;

public interface PostLikeRepository {
    Optional<PostLike> save(PostLike postLike);
    void deleteById(Long id);
    List<Post> findPostLikesByPostId(Long postId);
}
