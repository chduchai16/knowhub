package com.spring.knowhub.application.queries.notification;

import com.spring.knowhub.application.buses.Query;
import com.spring.knowhub.domain.models.notification.UserNotification;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
public class GetUserNotificationsQuery implements Query<Page<UserNotification>> {
    private Long userId;
    private int page;
    private int size;
}
