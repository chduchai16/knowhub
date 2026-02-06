package com.spring.knowhub.application.commands.post.postlike;

import com.spring.knowhub.application.buses.Command;

import lombok.Data;

@Data
public class CreatePostLikeCommand implements Command<Long> {
    private Long userId;
    private Long postId;

    public CreatePostLikeCommand(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
