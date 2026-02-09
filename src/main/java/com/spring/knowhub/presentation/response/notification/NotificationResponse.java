package com.spring.knowhub.presentation.response.notification;

import com.spring.knowhub.domain.enums.notification.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {
    private Long id;
    private NotificationType type;
    private String title;
    private String content;

    // thông tin người thao tác
    private Long actorId;
    private String actorUsername;
    private String actorAvatarUrl;

    // thông tin tham chiếu
    private Long referenceId;
    private String referenceType;

    // trạng thái đã đọc
    private Boolean isRead;
    private LocalDateTime readAt;

    private LocalDateTime createdAt;
}
