package com.spring.knowhub.application.commands.comment;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.domain.enums.notification.NotificationType;
import com.spring.knowhub.domain.models.comment.Comment;
import com.spring.knowhub.domain.models.notification.Notification;
import com.spring.knowhub.domain.models.notification.UserNotification;
import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.comment.CommentRepository;
import com.spring.knowhub.domain.repositories.notification.NotificationRepository;
import com.spring.knowhub.domain.repositories.notification.UserNotificationRepository;
import com.spring.knowhub.domain.repositories.post.PostRepository;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
public class CreateCommentCommandHandler implements CommandHandler<CreateCommentCommand, Comment> {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final NotificationRepository notificationRepository;
    private final UserNotificationRepository userNotificationRepository;

    @Override
    public boolean supports(Object command) {
        return command instanceof CreateCommentCommand;
    }

    @Override
    public Comment handle(CreateCommentCommand command) {
        Comment comment = new Comment();

        Post post = postRepository.findById(command.getPostId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết"));
        comment.setPost(post);

        User user = userRepository.findById(command.getUserId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        comment.setUser(user);

        Comment parent = null;
        if (command.getParentId() != null) {
            parent = commentRepository.findById(command.getParentId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy bình luận cha"));
            comment.setParent(parent);
            // rootId = rootId của parent (nếu có), hoặc id của parent (nếu parent là root)
            comment.setRootId(parent.getRootId() != null ? parent.getRootId() : parent.getId());
        }
        // comment gốc: rootId = null (mặc định)

        comment.setContent(command.getContent());

        Comment savedComment = commentRepository.save(comment);
        savedComment.setUser(user);
        // notification cho chủ bài viết (COMMENT)
        if (post.getUser() != null && !post.getUser().getId().equals(command.getUserId())) {
            Notification notification = new Notification();
            notification.setType(NotificationType.COMMENT);
            notification.setTitle("Bình luận mới");
            notification.setContent(user.getUsername() + " đã bình luận bài viết của bạn");
            notification.setActor(user);
            notification.setReferenceId(command.getPostId());
            notification.setReferenceType("POST");
            Notification savedNotification = notificationRepository.save(notification);

            User receiver = new User();
            receiver.setId(post.getUser().getId());

            UserNotification userNotification = new UserNotification();
            userNotification.setUser(receiver);
            userNotification.setNotification(savedNotification);
            userNotification.setIsRead(false);
            userNotificationRepository.save(userNotification);
        }

        // notification cho chủ comment cha (REPLY)
        if (parent != null && parent.getUser() != null && !parent.getUser().getId().equals(command.getUserId())) {
            Notification notification = new Notification();
            notification.setType(NotificationType.REPLY);
            notification.setTitle("Phản hồi mới");
            notification.setContent(user.getUsername() + " đã trả lời bình luận của bạn");
            notification.setActor(user);
            notification.setReferenceId(savedComment.getId());
            notification.setReferenceType("COMMENT");
            Notification savedNotification = notificationRepository.save(notification);

            User receiver = new User();
            receiver.setId(parent.getUser().getId());

            UserNotification userNotification = new UserNotification();
            userNotification.setUser(receiver);
            userNotification.setNotification(savedNotification);
            userNotification.setIsRead(false);
            userNotificationRepository.save(userNotification);
        }

        return savedComment;
    }
}
