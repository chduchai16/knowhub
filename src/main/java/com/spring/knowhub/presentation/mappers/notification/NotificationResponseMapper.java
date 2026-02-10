package com.spring.knowhub.presentation.mappers.notification;

import com.spring.knowhub.domain.models.notification.Notification;
import com.spring.knowhub.domain.models.notification.UserNotification;
import com.spring.knowhub.presentation.response.notification.NotificationResponse;
import org.springframework.stereotype.Component;

@Component
public class NotificationResponseMapper {

    public NotificationResponse fromUserNotificationToResponse(UserNotification userNotification) {
        if (userNotification == null)
            return null;

        Notification notification = userNotification.getNotification();
        if (notification == null)
            return null;

        NotificationResponse.NotificationResponseBuilder builder = NotificationResponse.builder()
                .id(userNotification.getId())
                .type(notification.getType())
                .title(notification.getTitle())
                .content(notification.getContent())
                .referenceId(notification.getReferenceId())
                .referenceType(notification.getReferenceType())
                .postId(notification.getPostId())
                .isRead(userNotification.getIsRead())
                .readAt(userNotification.getReadAt())
                .createdAt(notification.getCreatedAt());

        // Map actor info
        if (notification.getActor() != null) {
            builder.actorId(notification.getActor().getId())
                    .actorUsername(notification.getActor().getUsername())
                    .actorAvatarUrl(notification.getActor().getAvatarUrl());
        }

        return builder.build();
    }
}
