package com.spring.knowhub.infrastructure.exceptions.message;

public class MessageRepositoryException extends RuntimeException {
    public MessageRepositoryException(String message) {
        super(message);
    }

    public static MessageRepositoryException saveFailed(String detail) {
        return new MessageRepositoryException("Lỗi khi lưu tin nhắn: " + detail);
    }

    public static MessageRepositoryException findFailed(String detail) {
        return new MessageRepositoryException("Lỗi khi tìm tin nhắn: " + detail);
    }

    public static MessageRepositoryException deleteFailed(String detail) {
        return new MessageRepositoryException("Lỗi khi xóa tin nhắn: " + detail);
    }
}
