package com.spring.knowhub.application.validators.post.postlike;

import com.spring.knowhub.application.commands.post.postlike.CreatePostLikeCommand;
import com.spring.knowhub.application.exceptions.post.postlike.CreatePostLikeException;

public class CreatePostLikeValidator {
    public static void validate(CreatePostLikeCommand command) {
        if (command.getUserId() == null) {
            throw CreatePostLikeException.missingRequireField("userId");
        }
        if (command.getPostId() == null) {
            throw CreatePostLikeException.missingRequireField("postId");
        }
    }
}
