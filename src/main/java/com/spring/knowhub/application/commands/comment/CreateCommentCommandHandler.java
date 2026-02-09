package com.spring.knowhub.application.commands.comment;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.domain.models.comment.Comment;
import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.comment.CommentRepository;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateCommentCommandHandler implements CommandHandler<CreateCommentCommand, Comment> {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    public boolean supports(Object command) {
        return command instanceof CreateCommentCommand;
    }

    @Override
    public Comment handle(CreateCommentCommand command) {
        Comment comment = new Comment();

        Post post = new Post();
        post.setId(command.getPostId());
        comment.setPost(post);

        User user = userRepository.findById(command.getUserId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        comment.setUser(user);

        if (command.getParentId() != null) {
            Comment parent = new Comment();
            parent.setId(command.getParentId());
            comment.setParent(parent);
        }

        comment.setContent(command.getContent());

        Comment savedComment = commentRepository.save(comment);
        savedComment.setUser(user);
        return savedComment;
    }
}
