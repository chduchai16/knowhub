package com.spring.knowhub.domain.exceptions.post.postlike;

public class PostAlreadyLikedException extends PostLikeDomainException {
    public PostAlreadyLikedException(Long postId, Long userId) {
        super("POST_ALREADY_LIKED", "User với ID " + userId + " đã thích bài viết với ID " + postId);
    }

    public static PostAlreadyLikedException of(Long postId, Long userId) {
        return new PostAlreadyLikedException(postId, userId);
    }
}
