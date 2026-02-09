package com.spring.knowhub.application.commands.comment;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.domain.exceptions.comment.CommentNotFoundException;
import com.spring.knowhub.domain.models.comment.Comment;
import com.spring.knowhub.domain.repositories.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteCommentCommandHandler implements CommandHandler<DeleteCommentCommand, Void> {

    private final CommentRepository commentRepository;

    @Override
    public boolean supports(Object command) {
        return command instanceof DeleteCommentCommand;
    }

    @Override
    public Void handle(DeleteCommentCommand command) {
        Comment comment = commentRepository.findById(command.getId())
                .orElseThrow(() -> CommentNotFoundException.withId(command.getId()));

        if (!comment.getUser().getId().equals(command.getUserId())) {
            throw new RuntimeException("Bạn không có quyền xóa bình luận này");
        }

        commentRepository.deleteById(command.getId());
        return null;
    }
}
