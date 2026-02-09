package com.spring.knowhub.infrastructure.repositories.impls.notification;

import com.spring.knowhub.domain.exceptions.notification.NotificationNotFoundException;
import com.spring.knowhub.domain.models.notification.Notification;
import com.spring.knowhub.domain.repositories.notification.NotificationRepository;
import com.spring.knowhub.infrastructure.entities.notification.NotificationEntity;
import com.spring.knowhub.infrastructure.exceptions.notification.NotificationMapperException;
import com.spring.knowhub.infrastructure.exceptions.notification.NotificationRepositoryException;
import com.spring.knowhub.infrastructure.mappers.notification.NotificationMapper;
import com.spring.knowhub.infrastructure.repositories.jpas.notification.JpaNotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepository {

    private final JpaNotificationRepository jpaNotificationRepository;
    private final NotificationMapper notificationMapper;

    @Override
    public Notification save(Notification notification) {
        log.info("Lưu thông báo mới hoặc cập nhật thông báo với ID: {}", notification.getId());
        try {
            NotificationEntity entity = notificationMapper.fromDomainToEntity(notification);
            NotificationEntity savedEntity = jpaNotificationRepository.save(entity);
            Notification savedNotification = notificationMapper.fromEntityToDomain(savedEntity);
            log.info("Lưu thông báo thành công với ID: {}", savedNotification.getId());
            return savedNotification;
        } catch (NotificationMapperException ex) {
            log.error("Lỗi ánh xạ thông báo khi lưu. Chi tiết: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi lưu thông báo. Chi tiết: {}", ex.getMessage());
            throw NotificationRepositoryException.saveFailed(ex.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Xóa thông báo với ID: {}", id);
        try {
            jpaNotificationRepository.deleteById(id);
            log.info("Xóa thông báo thành công với ID: {}", id);
        } catch (Exception ex) {
            log.error("Lỗi khi xóa thông báo với ID: {}. Chi tiết: {}", id, ex.getMessage());
            throw NotificationRepositoryException.deleteFailed(ex.getMessage());
        }
    }

    @Override
    public Optional<Notification> findById(Long id) {
        log.info("Tìm thông báo với ID: {}", id);
        try {
            Optional<Notification> notificationOptional = jpaNotificationRepository.findById(id)
                    .map(notificationMapper::fromEntityToDomain);
            if (notificationOptional.isEmpty()) {
                throw NotificationNotFoundException.withId(id);
            }
            log.info("Tìm thấy thông báo với ID: {}", id);
            return notificationOptional;
        } catch (NotificationNotFoundException ex) {
            log.warn("Không tìm thấy thông báo với ID: {}", id);
            throw ex;
        } catch (NotificationMapperException ex) {
            log.error("Lỗi ánh xạ thông báo với ID: {}. Chi tiết: {}", id, ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm thông báo với ID: {}. Chi tiết: {}", id, ex.getMessage());
            throw NotificationRepositoryException.findFailed(ex.getMessage());
        }
    }

    @Override
    public Page<Notification> findNotificationsPaged(Pageable pageable) {
        log.info("Tìm thông báo phân trang với thông số: {}", pageable);
        try {
            Page<Notification> notificationPage = jpaNotificationRepository.findAll(pageable)
                    .map(notificationMapper::fromEntityToDomain);
            log.info("Tìm thấy {} thông báo phân trang", notificationPage.getTotalElements());
            return notificationPage;
        } catch (NotificationMapperException ex) {
            log.error("Lỗi ánh xạ thông báo phân trang. Chi tiết: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi tìm thông báo phân trang. Chi tiết: {}", ex.getMessage());
            throw NotificationRepositoryException.findFailed(ex.getMessage());
        }
    }
}
