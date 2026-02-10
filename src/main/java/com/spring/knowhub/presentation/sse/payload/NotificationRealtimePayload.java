package com.spring.knowhub.presentation.sse.payload;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRealtimePayload {
    private Long id;
    private String type;
    private String title;
    private String content;

    // thông tin người thao tác
    private Long actorId;
    private String actorUsername;
    private String actorAvatarUrl;

    // thông tin tham chiếu
    private Long referenceId;
    private String referenceType;
    private Long postId;

    // trạng thái đã đọc
    private Boolean isRead;
    private LocalDateTime readAt;

    private LocalDateTime createdAt;
}
