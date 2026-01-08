package com.spring.knowhub.infrastructure.exceptions.post.post;

public class PostRepositoryException extends PostInfrastructureException {
    public PostRepositoryException(String message) {
        super("POST_REPOSITORY_ERROR", message);
    }

    public PostRepositoryException(String message, Throwable cause) {
        super("POST_REPOSITORY_ERROR", message, cause);
    }

    public static PostRepositoryException saveFailed(String message) {
        return new PostRepositoryException("Lỗi khi lưu bài viết: " + message);
    }

    public static PostRepositoryException findFailed(String message) {
        return new PostRepositoryException("Lỗi khi tìm bài viết: " + message);
    }

    public static PostRepositoryException deleteFailed(String message) {
        return new PostRepositoryException("Lỗi khi xóa bài viết: " + message);
    }

}
