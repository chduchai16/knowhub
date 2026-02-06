package com.spring.knowhub.infrastructure.repositories.impls.notification;

import com.spring.knowhub.domain.models.notification.Notification;
import com.spring.knowhub.domain.repositories.notification.NotificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class NotificationRepositoryImpl implements NotificationRepository {
    @Override
    public Notification save(Notification notification) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<Notification> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<Notification> findNotificationsPaged(Pageable pageable) {
        return null;
    }
}
