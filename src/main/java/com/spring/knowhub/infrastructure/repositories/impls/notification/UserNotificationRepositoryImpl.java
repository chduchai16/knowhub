package com.spring.knowhub.infrastructure.repositories.impls.notification;

import com.spring.knowhub.domain.exceptions.notification.UserNotificationNotFoundException;
import com.spring.knowhub.domain.models.notification.UserNotification;
import com.spring.knowhub.domain.repositories.notification.UserNotificationRepository;
import com.spring.knowhub.infrastructure.entities.notification.UserNotificationEntity;
import com.spring.knowhub.infrastructure.exceptions.notification.NotificationMapperException;
import com.spring.knowhub.infrastructure.exceptions.notification.NotificationRepositoryException;
import com.spring.knowhub.infrastructure.mappers.notification.UserNotificationMapper;
import com.spring.knowhub.infrastructure.repositories.jpas.notification.JpaUserNotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserNotificationRepositoryImpl implements UserNotificationRepository {

    private final JpaUserNotificationRepository jpaUserNotificationRepository;
    private final UserNotificationMapper userNotificationMapper;

    @Override
    public UserNotification save(UserNotification userNotification) {
        log.info("Lưu UserNotification cho user ID: {}",
                userNotification.getUser() != null ? userNotification.getUser().getId() : "null");
        try {
            UserNotificationEntity entity = userNotificationMapper.fromDomainToEntity(userNotification);
            UserNotificationEntity savedEntity = jpaUserNotificationRepository.save(entity);
            UserNotification saved = userNotificationMapper.fromEntityToDomain(savedEntity);
            log.info("Lưu UserNotification thành công với ID: {}", saved.getId());
            return saved;
        } catch (NotificationMapperException ex) {
            log.error("Lỗi ánh xạ UserNotification khi lưu. Chi tiết: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi lưu UserNotification. Chi tiết: {}", ex.getMessage());
            throw NotificationRepositoryException.saveFailed(ex.getMessage());
        }
    }

    @Override
    public List<UserNotification> saveAll(List<UserNotification> userNotifications) {
        log.info("Lưu {} UserNotifications", userNotifications.size());
        try {
            List<UserNotificationEntity> entities = userNotifications.stream()
                    .map(userNotificationMapper::fromDomainToEntity)
                    .collect(Collectors.toList());
            List<UserNotificationEntity> savedEntities = jpaUserNotificationRepository.saveAll(entities);
            List<UserNotification> savedList = savedEntities.stream()
                    .map(userNotificationMapper::fromEntityToDomain)
                    .collect(Collectors.toList());
            log.info("Lưu thành công {} UserNotifications", savedList.size());
            return savedList;
        } catch (NotificationMapperException ex) {
            log.error("Lỗi ánh xạ khi lưu nhiều UserNotifications. Chi tiết: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi lưu nhiều UserNotifications. Chi tiết: {}", ex.getMessage());
            throw NotificationRepositoryException.saveFailed(ex.getMessage());
        }
    }

    @Override
    public Optional<UserNotification> findById(Long id) {
        log.info("Tìm UserNotification với ID: {}", id);
        try {
            Optional<UserNotification> result = jpaUserNotificationRepository.findById(id)
                    .map(userNotificationMapper::fromEntityToDomain);
            if (result.isEmpty()) {
                throw UserNotificationNotFoundException.withId(id);
            }
            log.info("Tìm thấy UserNotification với ID: {}", id);
            return result;
        } catch (UserNotificationNotFoundException ex) {
            log.warn("Không tìm thấy UserNotification với ID: {}", id);
            throw ex;
        } catch (NotificationMapperException ex) {
            log.error("Lỗi ánh xạ UserNotification với ID: {}. Chi tiết: {}", id, ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm UserNotification với ID: {}. Chi tiết: {}", id, ex.getMessage());
            throw NotificationRepositoryException.findFailed(ex.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Xóa UserNotification với ID: {}", id);
        try {
            jpaUserNotificationRepository.deleteById(id);
            log.info("Xóa UserNotification thành công với ID: {}", id);
        } catch (Exception ex) {
            log.error("Lỗi khi xóa UserNotification với ID: {}. Chi tiết: {}", id, ex.getMessage());
            throw NotificationRepositoryException.deleteFailed(ex.getMessage());
        }
    }

    @Override
    public Page<UserNotification> findByUserIdPaged(Long userId, Pageable pageable) {
        log.info("Lấy danh sách notifications cho user ID: {}, pageable: {}", userId, pageable);
        try {
            Page<UserNotification> result = jpaUserNotificationRepository
                    .findByUserIdOrderByCreatedAtDesc(userId, pageable)
                    .map(userNotificationMapper::fromEntityToDomain);
            log.info("Tìm thấy {} notifications cho user ID: {}", result.getTotalElements(), userId);
            return result;
        } catch (NotificationMapperException ex) {
            log.error("Lỗi ánh xạ notifications cho user ID: {}. Chi tiết: {}", userId, ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm notifications cho user ID: {}. Chi tiết: {}", userId, ex.getMessage());
            throw NotificationRepositoryException.findFailed(ex.getMessage());
        }
    }

    @Override
    public Long countUnreadByUserId(Long userId) {
        log.info("Đếm số notifications chưa đọc cho user ID: {}", userId);
        try {
            Long count = jpaUserNotificationRepository.countUnreadByUserId(userId);
            log.info("Số notifications chưa đọc cho user ID {}: {}", userId, count);
            return count != null ? count : 0L;
        } catch (Exception ex) {
            log.error("Lỗi khi đếm notifications chưa đọc cho user ID: {}. Chi tiết: {}", userId, ex.getMessage());
            return 0L;
        }
    }

    @Override
    @Transactional
    public void markAsRead(Long id) {
        log.info("Đánh dấu đã đọc notification ID: {}", id);
        try {
            jpaUserNotificationRepository.markAsRead(id);
            log.info("Đánh dấu đã đọc thành công notification ID: {}", id);
        } catch (Exception ex) {
            log.error("Lỗi khi đánh dấu đã đọc notification ID: {}. Chi tiết: {}", id, ex.getMessage());
            throw NotificationRepositoryException.updateFailed(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public void markAllAsReadByUserId(Long userId) {
        log.info("Đánh dấu tất cả notifications đã đọc cho user ID: {}", userId);
        try {
            jpaUserNotificationRepository.markAllAsReadByUserId(userId);
            log.info("Đánh dấu tất cả notifications đã đọc thành công cho user ID: {}", userId);
        } catch (Exception ex) {
            log.error("Lỗi khi đánh dấu tất cả notifications đã đọc cho user ID: {}. Chi tiết: {}", userId,
                    ex.getMessage());
            throw NotificationRepositoryException.updateFailed(ex.getMessage());
        }
    }
}
