package com.spring.knowhub.application.validators.post.tag;

import com.spring.knowhub.application.commands.post.tag.DeleteTagCommand;
import com.spring.knowhub.application.exceptions.post.tag.DeleteTagException;

public class DeleteTagValidator {
    public static void validate (DeleteTagCommand command) {
        if(command.getId() == null) {
            throw DeleteTagException.misingRequiredField("id") ;
        }
    }
}
