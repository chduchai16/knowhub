package com.spring.knowhub.infrastructure.repositories.jpas.notification;

import com.spring.knowhub.infrastructure.entities.notification.UserNotificationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserNotificationRepository extends JpaRepository<UserNotificationEntity, Long> {

    Page<UserNotificationEntity> findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);

    @Query("SELECT COUNT(un) FROM UserNotificationEntity un WHERE un.user.id = :userId AND un.isRead = false")
    Long countUnreadByUserId(@Param("userId") Long userId);

    @Modifying
    @Query("UPDATE UserNotificationEntity un SET un.isRead = true, un.readAt = CURRENT_TIMESTAMP WHERE un.id = :id")
    void markAsRead(@Param("id") Long id);

    @Modifying
    @Query("UPDATE UserNotificationEntity un SET un.isRead = true, un.readAt = CURRENT_TIMESTAMP WHERE un.user.id = :userId AND un.isRead = false")
    void markAllAsReadByUserId(@Param("userId") Long userId);
}
