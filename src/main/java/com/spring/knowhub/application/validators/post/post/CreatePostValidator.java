package com.spring.knowhub.application.validators.post.post;

import com.spring.knowhub.application.commands.post.post.CreatePostCommand;
import com.spring.knowhub.application.exceptions.post.post.CreatePostException;
import com.spring.knowhub.domain.constants.PostConstants;
import com.spring.knowhub.domain.enums.post.PostStatus;
import com.spring.knowhub.domain.enums.post.Privacy;
import com.spring.knowhub.domain.exceptions.post.post.InvalidPostException;

import java.util.ArrayList;

public class CreatePostValidator {
    public static void validate (CreatePostCommand command) {
        if(command.getUserId() == null || command.getUserId() <= 0) {
            throw CreatePostException.misingRequiredFields("user id");
        }
        if(command.getPrivacy() == null) {
            throw CreatePostException.misingRequiredFields("privacy");
        }
        if(command.getStatus() == null) {
            throw CreatePostException.misingRequiredFields("status");
        }
        if(!PostStatus.contains(command.getStatus().toUpperCase())) {
            throw InvalidPostException.invalidStatus(command.getStatus());
        }
        if(!Privacy.contains(command.getPrivacy().toUpperCase())) {
            throw InvalidPostException.privacyInvalid();
        }
        if(command.getContent() != null && command.getContent().length() > PostConstants.MAX_CONTENT_LENGTH) {
            throw InvalidPostException.contentTooLong(PostConstants.MAX_CONTENT_LENGTH) ;
        }
        if(command.getMediaIds() != null && command.getMediaIds().size() > PostConstants.MAX_MEDIA_PER_POST) {
            throw InvalidPostException.mediaLimitExceeded(PostConstants.MAX_MEDIA_PER_POST) ;
        }
        if(command.getTagIds().size() > PostConstants.MAX_TAGS_PER_POST) {
            throw InvalidPostException.tagLimitExceeded(PostConstants.MAX_TAGS_PER_POST) ;
        }
        if (command.getMediaIds() == null) {
            command.setMediaIds(new ArrayList<>());
        }
    }
}
