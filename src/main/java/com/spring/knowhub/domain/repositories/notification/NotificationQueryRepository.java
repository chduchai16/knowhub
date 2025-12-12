package com.spring.knowhub.domain.repositories.notification;

import com.spring.knowhub.domain.models.notification.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NotificationQueryRepository {
    Optional<Notification> findById(Long id);
    Page<Notification> findNotificationsPaged(Pageable pageable);
}
