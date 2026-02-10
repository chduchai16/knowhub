package com.spring.knowhub.application.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationCreatedEvent {
    private Long notificationId;
    private Long toUserId;
}
