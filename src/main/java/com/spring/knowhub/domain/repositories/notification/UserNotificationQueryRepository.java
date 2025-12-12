package com.spring.knowhub.domain.repositories.notification;

import com.spring.knowhub.domain.models.notification.UserNotification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserNotificationQueryRepository {
    Optional<UserNotification> findById(Long id);
    Page<UserNotification> findUserNotificationsPaged(Pageable pageable);
}
