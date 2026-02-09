package com.spring.knowhub.infrastructure.exceptions.notification;

public class NotificationRepositoryException extends NotificationInfrastructureException {
    public NotificationRepositoryException(String message) {
        super("NOTIFICATION_REPOSITORY_ERROR", message);
    }

    public NotificationRepositoryException(String message, Throwable cause) {
        super("NOTIFICATION_REPOSITORY_ERROR", message, cause);
    }

    public static NotificationRepositoryException saveFailed(String message) {
        return new NotificationRepositoryException("Lỗi khi lưu thông báo: " + message);
    }

    public static NotificationRepositoryException findFailed(String message) {
        return new NotificationRepositoryException("Lỗi khi tìm thông báo: " + message);
    }

    public static NotificationRepositoryException deleteFailed(String message) {
        return new NotificationRepositoryException("Lỗi khi xóa thông báo: " + message);
    }

    public static NotificationRepositoryException updateFailed(String message) {
        return new NotificationRepositoryException("Lỗi khi cập nhật thông báo: " + message);
    }
}
