package com.spring.knowhub.application.commands.comment;

import com.spring.knowhub.application.buses.Command;
import com.spring.knowhub.domain.models.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateCommentCommand implements Command<Comment> {
    private Long postId;
    private Long userId;
    private Long parentId;
    private String content;
}
