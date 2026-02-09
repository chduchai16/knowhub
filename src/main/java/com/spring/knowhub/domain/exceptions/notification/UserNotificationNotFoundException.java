package com.spring.knowhub.domain.exceptions.notification;

public class UserNotificationNotFoundException extends NotificationDomainException {
    public UserNotificationNotFoundException(String message) {
        super("USER_NOTIFICATION_NOT_FOUND", message);
    }

    public static UserNotificationNotFoundException withId(Long id) {
        return new UserNotificationNotFoundException("Thông báo người dùng với ID " + id + " không tồn tại.");
    }

    public static UserNotificationNotFoundException forUser(Long userId) {
        return new UserNotificationNotFoundException("Không tìm thấy thông báo cho người dùng ID " + userId);
    }
}
