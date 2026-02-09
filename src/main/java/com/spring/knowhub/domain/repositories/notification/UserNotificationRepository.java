package com.spring.knowhub.domain.repositories.notification;

import com.spring.knowhub.domain.models.notification.UserNotification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserNotificationRepository {
    UserNotification save(UserNotification userNotification);
    List<UserNotification> saveAll(List<UserNotification> userNotifications);
    Optional<UserNotification> findById(Long id);
    void deleteById(Long id);
    Page<UserNotification> findByUserIdPaged(Long userId, Pageable pageable);
    Long countUnreadByUserId(Long userId);
    void markAsRead(Long id);
    void markAllAsReadByUserId(Long userId);
}
