package com.spring.knowhub.application.commands.notification;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.domain.repositories.notification.UserNotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MarkAllNotificationsReadCommandHandler implements CommandHandler<MarkAllNotificationsReadCommand, Void> {

    private final UserNotificationRepository userNotificationRepository;

    @Override
    public boolean supports(Object command) {
        return command instanceof MarkAllNotificationsReadCommand;
    }

    @Override
    public Void handle(MarkAllNotificationsReadCommand command) {
        log.info("Đánh dấu tất cả notifications đã đọc cho user ID: {}", command.getUserId());
        userNotificationRepository.markAllAsReadByUserId(command.getUserId());
        return null;
    }
}
