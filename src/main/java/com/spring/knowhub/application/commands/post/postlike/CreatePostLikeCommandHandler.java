package com.spring.knowhub.application.commands.post.postlike;

import com.spring.knowhub.application.events.NotificationCreatedEvent;
import com.spring.knowhub.domain.enums.notification.NotificationType;
import com.spring.knowhub.domain.models.notification.Notification;
import com.spring.knowhub.domain.models.notification.UserNotification;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.notification.NotificationRepository;
import com.spring.knowhub.domain.repositories.notification.UserNotificationRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.validators.post.postlike.CreatePostLikeValidator;
import com.spring.knowhub.domain.exceptions.post.post.PostNotFoundException;
import com.spring.knowhub.domain.exceptions.post.postlike.PostAlreadyLikedException;
import com.spring.knowhub.domain.exceptions.user.user.UserNotFoundException;
import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.domain.models.post.PostLike;
import com.spring.knowhub.domain.repositories.post.PostLikeRepository;
import com.spring.knowhub.domain.repositories.post.PostRepository;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;

import jakarta.transaction.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class CreatePostLikeCommandHandler implements CommandHandler<CreatePostLikeCommand, Long> {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostLikeRepository postLikeRepository;
    private final NotificationRepository notificationRepository;
    private final UserNotificationRepository userNotificationRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public boolean supports(Object command) {
        return command instanceof CreatePostLikeCommand;
    }

    @Override
    public Long handle(CreatePostLikeCommand command) {
        CreatePostLikeValidator.validate(command);

        if (postLikeRepository.findByPostIdAndUserId(command.getPostId(), command.getUserId()).isPresent()) {
            throw PostAlreadyLikedException.of(command.getPostId(), command.getUserId());
        }

        Post existingPost = postRepository.findById(command.getPostId())
                .orElseThrow(() -> PostNotFoundException.withId(command.getPostId()));
        User existingUser = userRepository.findById(command.getUserId())
                .orElseThrow(() -> UserNotFoundException.byId(command.getUserId()));

        PostLike postLike = new PostLike(null, existingPost, existingUser);
        PostLike savedPostLike = postLikeRepository.save(postLike);

        // tạo notification cho chủ bài viết
        if (existingPost.getUser() != null && !existingPost.getUser().getId().equals(command.getUserId())) {
            Notification notification = new Notification();
            notification.setType(NotificationType.LIKE);
            notification.setTitle("Lượt thích mới");
            notification.setContent(existingUser.getUsername() + " đã thích bài viết của bạn");
            notification.setActor(existingUser);
            notification.setReferenceId(command.getPostId());
            notification.setReferenceType("POST");
            notification.setPostId(command.getPostId());
            Notification savedNotification = notificationRepository.save(notification);

            User receiver = new User();
            receiver.setId(existingPost.getUser().getId());

            UserNotification userNotification = new UserNotification();
            userNotification.setUser(receiver);
            userNotification.setNotification(savedNotification);
            userNotification.setIsRead(false);
            userNotificationRepository.save(userNotification);

            // publish event để gửi realtime qua SSE
            eventPublisher.publishEvent(new NotificationCreatedEvent(
                    savedNotification.getId(), existingPost.getUser().getId()));
        }

        return savedPostLike.getId();
    }
}