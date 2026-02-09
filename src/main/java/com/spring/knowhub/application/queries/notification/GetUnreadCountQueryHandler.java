package com.spring.knowhub.application.queries.notification;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.repositories.notification.UserNotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetUnreadCountQueryHandler implements QueryHandler<GetUnreadCountQuery, Long> {

    private final UserNotificationRepository userNotificationRepository;

    @Override
    public boolean supports(Object query) {
        return query instanceof GetUnreadCountQuery;
    }

    @Override
    public Long handle(GetUnreadCountQuery query) {
        log.info("Đếm notifications chưa đọc cho user ID: {}", query.getUserId());
        return userNotificationRepository.countUnreadByUserId(query.getUserId());
    }
}
