package com.spring.knowhub.infrastructure.exceptions.post.postlike;

public class PostLikeRepositoryException extends PostLikeInfrastructureException {
    public PostLikeRepositoryException(String message) {
        super(message, "POST_LIKE_REPOSITORY_EXCEPTION");
    }

    public PostLikeRepositoryException(String message, Throwable cause) {
        super(message, "POST_LIKE_REPOSITORY_EXCEPTION", cause);
    }
    
    public static PostLikeRepositoryException saveFailed(String message) {
        return new PostLikeRepositoryException(message);
    }

    public static PostLikeRepositoryException findFailed(String message) {
        return new PostLikeRepositoryException(message);
    }

    public static PostLikeRepositoryException deleteFailed(String message) {
        return new PostLikeRepositoryException(message);
    }
}
