package com.spring.knowhub.domain.exceptions.notification;

public class NotificationNotFoundException extends NotificationDomainException {
    public NotificationNotFoundException(String message) {
        super("NOTIFICATION_NOT_FOUND", message);
    }

    public static NotificationNotFoundException withId(Long id) {
        return new NotificationNotFoundException("Thông báo với ID " + id + " không tồn tại.");
    }
}
