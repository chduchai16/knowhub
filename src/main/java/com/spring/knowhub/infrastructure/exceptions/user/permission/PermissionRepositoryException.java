package com.spring.knowhub.infrastructure.exceptions.user.permission;

public class PermissionRepositoryException extends PermissionInfrastructureException {
    public PermissionRepositoryException(String message) {
        super("PERMISSION_REPOSITORY_ERROR" , message);
    }

    public PermissionRepositoryException(String message, Throwable cause) {
        super("PERMISSION_REPOSITORY_ERROR", message, cause);
    }

    public static PermissionRepositoryException saveFailed(String details) {
        return new PermissionRepositoryException("Lỗi khi lưu Permission vào database: " + details);
    }

    public static PermissionRepositoryException deleteFailed(String details) {
        return new PermissionRepositoryException("Lỗi khi xóa Permission: " + details);
    }

    public static PermissionRepositoryException findFailed(String details) {
        return new PermissionRepositoryException("Lỗi khi tìm kiếm Permission: " + details);
    }
}
