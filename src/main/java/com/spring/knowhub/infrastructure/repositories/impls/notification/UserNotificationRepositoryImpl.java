package com.spring.knowhub.infrastructure.repositories.impls.notification;

import com.spring.knowhub.domain.models.notification.UserNotification;
import com.spring.knowhub.domain.repositories.notification.UserNotificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class UserNotificationRepositoryImpl implements UserNotificationRepository {
    @Override
    public Optional<UserNotification> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<UserNotification> findUserNotificationsPaged(Pageable pageable) {
        return null;
    }

    @Override
    public UserNotification save(UserNotification userNotification) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
