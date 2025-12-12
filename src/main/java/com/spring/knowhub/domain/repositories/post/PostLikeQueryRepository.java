package com.spring.knowhub.domain.repositories.post;

import com.spring.knowhub.domain.models.post.Post;

import java.util.List;

public interface PostLikeQueryRepository {
    List<Post> findPostLikesByPostId(Long postId);
}
