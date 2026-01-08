package com.spring.knowhub.application.validators.post.post;

import com.spring.knowhub.application.commands.post.post.UpdatePostCommand;
import com.spring.knowhub.application.exceptions.post.post.UpdatePostException;
import com.spring.knowhub.domain.constants.PostConstants;
import com.spring.knowhub.domain.exceptions.post.post.InvalidPostException;

public class UpdatePostValidator {
    public static void validate (UpdatePostCommand command){
        if(command.getId() == null || command.getId() <= 0) {
            throw UpdatePostException.misingRequiredFields("id");
        }
        if(command.getPrivacy() == null) {
            throw UpdatePostException.misingRequiredFields("privacy");
        }
        if(command.getStatus() == null) {
            throw UpdatePostException.misingRequiredFields("status");
        }
        if(command.getUserId() == null || command.getUserId() <= 0) {
            throw UpdatePostException.misingRequiredFields("user id");
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

    }
}
