package com.spring.knowhub.domain.repositories.notification;

import com.spring.knowhub.domain.models.notification.UserNotification;

import java.util.Optional;

public interface UserNotificationCommandRepository {
    Optional<UserNotification> save(UserNotification userNotification);
    void deleteById(Long id);
}
