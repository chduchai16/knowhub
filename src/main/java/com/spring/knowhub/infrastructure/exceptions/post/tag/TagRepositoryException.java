package com.spring.knowhub.infrastructure.exceptions.post.tag;

public class TagRepositoryException extends TagInfrastructureException {

    public TagRepositoryException(String message) {
        super("TAG_REPOSITORY_ERROR", message);
    }

    public TagRepositoryException(String message, Throwable cause) {
        super("TAG_REPOSITORY_ERROR", message, cause);
    }

    public static TagRepositoryException saveFailed(String details) {
        return new TagRepositoryException("Lỗi khi lưu Tag vào kho dữ liệu. Chi tiết: " + details);
    }

    public static TagRepositoryException findFailed(String details) {
        return new TagRepositoryException("Lỗi khi tìm Tag từ kho dữ liệu. Chi tiết: " + details);
    }

    public static TagRepositoryException deleteFailed(String details) {
        return new TagRepositoryException("Lỗi khi xóa Tag khỏi kho dữ liệu. Chi tiết: " + details);
    }
}
