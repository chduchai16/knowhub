package com.spring.knowhub.infrastructure.exceptions.user.role;

public class RoleRepositoryException extends RoleInfrastructureException{

    public RoleRepositoryException(String message) {
        super("ROLE_REPOSITORY_ERROR", message);
    }

    public RoleRepositoryException(String message, Throwable cause) {
        super("ROLE_REPOSITORY_ERROR", message, cause);
    }

    public static RoleRepositoryException saveFailed(String details) {
        return new RoleRepositoryException("Lỗi khi lưu Role: " + details);
    }

    public static RoleRepositoryException deleteFailed(String details) {
        return new RoleRepositoryException("Lỗi khi xóa Role: " + details);
    }

    public static RoleRepositoryException findFailed(String details) {
        return new RoleRepositoryException("Lỗi khi tìm kiếm Role: " + details);
    }

    public static RoleRepositoryException updateFailed(String details) {
        return new RoleRepositoryException("Lỗi khi cập nhật Role: " + details);
    }
}
