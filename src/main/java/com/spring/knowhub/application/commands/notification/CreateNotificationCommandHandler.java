package com.spring.knowhub.application.commands.notification;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.domain.models.notification.Notification;
import com.spring.knowhub.domain.models.notification.UserNotification;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.notification.NotificationRepository;
import com.spring.knowhub.domain.repositories.notification.UserNotificationRepository;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateNotificationCommandHandler
        implements CommandHandler<CreateNotificationCommand, List<UserNotification>> {

    private final NotificationRepository notificationRepository;
    private final UserNotificationRepository userNotificationRepository;
    private final UserRepository userRepository;

    @Override
    public boolean supports(Object command) {
        return command instanceof CreateNotificationCommand;
    }

    @Override
    public List<UserNotification> handle(CreateNotificationCommand command) {
        log.info("Tạo notification type={} cho {} người nhận", command.getType(), command.getReceiverIds().size());

        // Lấy thông tin actor
        User actor = userRepository.findById(command.getActorId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng actor"));

        // Tạo Notification
        Notification notification = new Notification();
        notification.setType(command.getType());
        notification.setTitle(command.getTitle());
        notification.setContent(command.getContent());
        notification.setActor(actor);
        notification.setReferenceId(command.getReferenceId());
        notification.setReferenceType(command.getReferenceType());

        Notification savedNotification = notificationRepository.save(notification);

        // Tạo UserNotification cho từng người nhận
        List<UserNotification> userNotifications = new ArrayList<>();
        for (Long receiverId : command.getReceiverIds()) {
            // Không gửi notification cho chính mình
            if (receiverId.equals(command.getActorId())) {
                continue;
            }

            User receiver = new User();
            receiver.setId(receiverId);

            UserNotification userNotification = new UserNotification();
            userNotification.setUser(receiver);
            userNotification.setNotification(savedNotification);
            userNotification.setIsRead(false);

            userNotifications.add(userNotification);
        }

        if (!userNotifications.isEmpty()) {
            userNotifications = userNotificationRepository.saveAll(userNotifications);
        }

        log.info("Đã tạo {} UserNotifications thành công", userNotifications.size());
        return userNotifications;
    }
}
