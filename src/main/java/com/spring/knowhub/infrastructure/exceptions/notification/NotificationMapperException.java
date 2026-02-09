package com.spring.knowhub.infrastructure.exceptions.notification;

public class NotificationMapperException extends NotificationInfrastructureException {
    public NotificationMapperException(String message) {
        super("NOTIFICATION_MAPPER_ERROR", message);
    }

    public NotificationMapperException(String message, Throwable cause) {
        super("NOTIFICATION_MAPPER_ERROR", message, cause);
    }

    public static NotificationMapperException mappingFailed(String message) {
        return new NotificationMapperException("Lỗi ánh xạ thông báo: " + message);
    }
}
