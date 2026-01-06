package com.spring.knowhub.application.validators.post.tag;

import com.spring.knowhub.application.commands.post.tag.CreateTagCommand;
import com.spring.knowhub.application.exceptions.post.tag.CreateTagException;

public class CreateTagValidator {
    public static void validate (CreateTagCommand command) {
        if (command.getName() == null || command.getName().isEmpty()) {
            throw CreateTagException.misingRequiredFields("name") ;
        }
    }
}
