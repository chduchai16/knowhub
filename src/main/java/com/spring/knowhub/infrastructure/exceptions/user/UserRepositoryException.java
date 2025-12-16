package com.spring.knowhub.infrastructure.exceptions.user;

public class UserRepositoryException extends UserInfrastructureException {
    public UserRepositoryException(String message) {
        super("USER_REPOSITORY_ERROR", message);
    }

    public UserRepositoryException(String message, Throwable cause) {
        super("USER_REPOSITORY_ERROR", message, cause);
    }

    public static UserRepositoryException saveFailed(String details) {
        return new UserRepositoryException("Lỗi khi lưu User vào database: " + details);
    }

    public static UserRepositoryException deleteFailed(String details) {
        return new UserRepositoryException("Lỗi khi xóa User: " + details);
    }

    public static UserRepositoryException findFailed(String details) {
        return new UserRepositoryException("Lỗi khi tìm kiếm User: " + details);
    }

    public static UserRepositoryException constraintViolation(String fieldName, String value) {
        return new UserRepositoryException("Vi phạm constraint: Trường '" + fieldName + "' với giá trị '" + value + "' đã tồn tại");
    }
}
