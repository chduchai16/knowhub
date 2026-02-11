package com.spring.knowhub.domain.exceptions.message;

public class UnauthorizedMessageAccessException extends MessageDomainException {
    public UnauthorizedMessageAccessException(String message) {
        super("UNAUTHORIZED_MESSAGE_ACCESS", message);
    }

    public static UnauthorizedMessageAccessException modify(Long userId) {
        return new UnauthorizedMessageAccessException(
                "Người dùng " + userId + " không có quyền chỉnh sửa tin nhắn này");
    }

    public static UnauthorizedMessageAccessException delete(Long userId) {
        return new UnauthorizedMessageAccessException("Người dùng " + userId + " không có quyền xóa tin nhắn này");
    }
}
