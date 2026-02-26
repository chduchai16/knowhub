package com.spring.knowhub.application.queries.notification;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.models.notification.UserNotification;
import com.spring.knowhub.domain.repositories.notification.UserNotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class GetUserNotificationsQueryHandler
        implements QueryHandler<GetUserNotificationsQuery, Page<UserNotification>> {

    private final UserNotificationRepository userNotificationRepository;

    @Override
    public boolean supports(Object query) {
        return query instanceof GetUserNotificationsQuery;
    }

    @Override
    public Page<UserNotification> handle(GetUserNotificationsQuery query) {
        log.info("Lấy notifications cho user ID: {}, page: {}, size: {}",
                query.getUserId(), query.getPage(), query.getSize());
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize());
        return userNotificationRepository.findByUserIdPaged(query.getUserId(), pageable);
    }
}
