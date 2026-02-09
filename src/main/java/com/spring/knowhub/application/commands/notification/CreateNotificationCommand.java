package com.spring.knowhub.application.commands.notification;

import com.spring.knowhub.application.buses.Command;
import com.spring.knowhub.domain.enums.notification.NotificationType;
import com.spring.knowhub.domain.models.notification.UserNotification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateNotificationCommand implements Command<List<UserNotification>> {
    private NotificationType type;
    private String title;
    private String content;
    private Long actorId; // Người thực hiện hành động
    private List<Long> receiverIds; // Danh sách người nhận
    private Long referenceId; // ID đối tượng liên quan
    private String referenceType; // Loại đối tượng: POST, COMMENT, USER
}
