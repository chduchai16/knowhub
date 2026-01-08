package com.spring.knowhub.application.commands.post.post;

import com.spring.knowhub.application.buses.Command;
import com.spring.knowhub.domain.enums.post.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CreatePostCommand implements Command<Long> {
    private Long userId ;
    private String content ;
    private String privacy ;
    private List<Long> mediaIds ;
    private List<Long> tagIds ;
    private PostStatus status ;
}
