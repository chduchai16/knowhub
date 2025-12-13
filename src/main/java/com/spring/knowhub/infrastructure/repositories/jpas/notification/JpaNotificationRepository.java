package com.spring.knowhub.infrastructure.repositories.jpas.notification;

import com.spring.knowhub.infrastructure.entities.notification.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaNotificationRepository extends JpaRepository<NotificationEntity , Long> {
}
