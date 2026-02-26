package com.spring.knowhub.application.events.handlers;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import com.spring.knowhub.application.events.NotificationCreatedEvent;
import com.spring.knowhub.domain.exceptions.notification.NotificationNotFoundException;
import com.spring.knowhub.domain.models.notification.Notification;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.notification.NotificationRepository;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import com.spring.knowhub.presentation.sse.SseConnectionRegistry;
import com.spring.knowhub.presentation.sse.payload.NotificationRealtimePayload;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class PushNotificationRealtimeHandler {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final SseConnectionRegistry registry;

    @EventListener
    public void handle(NotificationCreatedEvent event) {

        Notification notification = notificationRepository.findById(event.getNotificationId())
                .orElseThrow(() -> NotificationNotFoundException.withId(event.getNotificationId()));

        User actor = null;
        if (notification.getActor() != null && notification.getActor().getId() != null) {
            actor = userRepository.findById(notification.getActor().getId()).orElse(null);
        }

        NotificationRealtimePayload payload = NotificationRealtimePayload.builder()
                .id(notification.getId())
                .type(notification.getType().name())
                .title(notification.getTitle())
                .content(notification.getContent())
                .actorId(actor != null ? actor.getId() : null)
                .actorUsername(actor != null ? actor.getUsername() : null)
                .actorAvatarUrl(actor != null ? actor.getAvatarUrl() : null)
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
