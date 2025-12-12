package com.spring.knowhub.domain.repositories.notification;

import com.spring.knowhub.domain.models.notification.Notification;

import java.util.Optional;

public interface NotificationCommandRepository {
    Optional<Notification> save(Notification notification);
    void deleteById(Long id);
}
