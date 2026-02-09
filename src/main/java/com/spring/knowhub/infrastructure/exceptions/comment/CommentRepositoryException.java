package com.spring.knowhub.infrastructure.exceptions.comment;

public class CommentRepositoryException extends CommentInfrastructureException {
    public CommentRepositoryException(String message) {
        super("COMMENT_REPOSITORY_ERROR", message);
    }

    public CommentRepositoryException(String message, Throwable cause) {
        super("COMMENT_REPOSITORY_ERROR", message, cause);
    }

    public static CommentRepositoryException saveFailed(String message) {
        return new CommentRepositoryException("Lỗi khi lưu bình luận: " + message);
    }

    public static CommentRepositoryException findFailed(String message) {
        return new CommentRepositoryException("Lỗi khi tìm bình luận: " + message);
    }

    public static CommentRepositoryException deleteFailed(String message) {
        return new CommentRepositoryException("Lỗi khi xóa bình luận: " + message);
    }
}
