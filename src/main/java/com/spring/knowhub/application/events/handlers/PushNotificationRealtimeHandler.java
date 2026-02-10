package com.spring.knowhub.application.events.handlers;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import com.spring.knowhub.application.events.NotificationCreatedEvent;
import com.spring.knowhub.domain.exceptions.notification.NotificationNotFoundException;
import com.spring.knowhub.domain.models.notification.Notification;
import com.spring.knowhub.domain.repositories.notification.NotificationRepository;
import com.spring.knowhub.presentation.sse.SseConnectionRegistry;
import com.spring.knowhub.presentation.sse.payload.NotificationRealtimePayload;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PushNotificationRealtimeHandler {
    private final NotificationRepository notificationRepository;
    private final SseConnectionRegistry registry;

    @EventListener
    public void handle(NotificationCreatedEvent event) {

        Notification notification = notificationRepository.findById(event.getNotificationId())
                .orElseThrow(() -> NotificationNotFoundException.withId(event.getNotificationId()));

        NotificationRealtimePayload payload = NotificationRealtimePayload.builder()
                .id(notification.getId())
                .type(notification.getType().name())
                .title(notification.getTitle())
                .content(notification.getContent())
                .actorId(notification.getActor() != null ? notification.getActor().getId() : null)
                .actorUsername(notification.getActor() != null ? notification.getActor().getUsername() : null)
                .actorAvatarUrl(notification.getActor() != null ? notification.getActor().getAvatarUrl() : null)
                .referenceId(notification.getReferenceId())
                .referenceType(notification.getReferenceType())
                .postId(notification.getPostId())
                .isRead(false)
                .readAt(null)
                .createdAt(notification.getCreatedAt())
                .build();

        registry.send(event.getToUserId(), payload);
    }
}
