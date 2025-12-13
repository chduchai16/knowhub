package com.spring.knowhub.domain.repositories.notification;

import com.spring.knowhub.domain.models.notification.UserNotification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserNotificationRepository {
    Optional<UserNotification> findById(Long id);
    Page<UserNotification> findUserNotificationsPaged(Pageable pageable);
    Optional<UserNotification> save(UserNotification userNotification);
    void deleteById(Long id);
}
