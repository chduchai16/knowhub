package com.spring.knowhub.application.commands.notification;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.domain.repositories.notification.UserNotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MarkNotificationReadCommandHandler implements CommandHandler<MarkNotificationReadCommand, Void> {

    private final UserNotificationRepository userNotificationRepository;

    @Override
    public boolean supports(Object command) {
        return command instanceof MarkNotificationReadCommand;
    }

    @Override
    public Void handle(MarkNotificationReadCommand command) {
        log.info("Đánh dấu đã đọc notification ID: {}", command.getUserNotificationId());
        userNotificationRepository.markAsRead(command.getUserNotificationId());
        return null;
    }
}
