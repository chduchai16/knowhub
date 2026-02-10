package com.spring.knowhub.application.commands.user.userfollow;

import com.spring.knowhub.application.events.NotificationCreatedEvent;
import com.spring.knowhub.domain.enums.notification.NotificationType;
import com.spring.knowhub.domain.models.notification.Notification;
import com.spring.knowhub.domain.models.notification.UserNotification;
import com.spring.knowhub.domain.repositories.notification.NotificationRepository;
import com.spring.knowhub.domain.repositories.notification.UserNotificationRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.domain.exceptions.user.user.UserNotFoundException;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.models.user.UserFollow;
import com.spring.knowhub.domain.repositories.user.UserFollowRepository;
import com.spring.knowhub.domain.repositories.user.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
public class CreateUserFollowCommandHandler implements CommandHandler<CreateUserFollowCommand, Long> {

    private final UserFollowRepository userFollowRepository;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private final UserNotificationRepository userNotificationRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public boolean supports(Object command) {
        return command instanceof CreateUserFollowCommand;
    }

    @Override
    public Long handle(CreateUserFollowCommand command) {
        User existingUser = userRepository.findById(command.getUserId())
                .orElseThrow(() -> UserNotFoundException.byId(command.getUserId()));
        User existingFollower = userRepository.findById(command.getFollowerId())
                .orElseThrow(() -> UserNotFoundException.byId(command.getFollowerId()));
        UserFollow savedUserFollow = userFollowRepository.save(new UserFollow(null, existingUser, existingFollower));

        // notification cho người được follow
        Notification notification = new Notification();
        notification.setType(NotificationType.FOLLOW);
        notification.setTitle("Người theo dõi mới");
        notification.setContent(existingFollower.getUsername() + " đã theo dõi bạn");
        notification.setActor(existingFollower);
        notification.setReferenceId(command.getFollowerId());
        notification.setReferenceType("USER");
        Notification savedNotification = notificationRepository.save(notification);

        User receiver = new User();
        receiver.setId(command.getUserId());

        UserNotification userNotification = new UserNotification();
        userNotification.setUser(receiver);
        userNotification.setNotification(savedNotification);
        userNotification.setIsRead(false);
        userNotificationRepository.save(userNotification);

        // publish event để gửi realtime qua SSE
        eventPublisher.publishEvent(new NotificationCreatedEvent(
                savedNotification.getId(), command.getUserId()));

        return savedUserFollow.getId();
    }
}
