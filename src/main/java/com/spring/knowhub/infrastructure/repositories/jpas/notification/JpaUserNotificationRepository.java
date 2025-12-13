package com.spring.knowhub.infrastructure.repositories.jpas.notification;

import com.spring.knowhub.infrastructure.entities.notification.UserNotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserNotificationRepository extends JpaRepository<UserNotificationEntity , Long> {
}
