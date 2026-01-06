package com.spring.knowhub.application.validators.post.tag;


import com.spring.knowhub.application.commands.post.tag.UpdateTagCommand;
import com.spring.knowhub.application.exceptions.post.tag.UpdateTagException;

public class UpdateTagValidator {
    public static void validate(UpdateTagCommand command) {
        if(command.getId() == null) {
            throw UpdateTagException.missingRequiredFields("id");
        }
        if(command.getName() == null || command.getName().isBlank()) {
            throw UpdateTagException.missingRequiredFields("name");
        }
    }
}
