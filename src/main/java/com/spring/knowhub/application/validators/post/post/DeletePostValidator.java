package com.spring.knowhub.application.validators.post.post;

import com.spring.knowhub.application.commands.post.post.DeletePostCommand;
import com.spring.knowhub.application.exceptions.post.post.DeletePostException;

public class DeletePostValidator {
    public static void validate(DeletePostCommand command) {
        if (command.getId() == null || command.getId() <= 0) {
            throw DeletePostException.missingRequiredFields("id");
        }
    }
}
