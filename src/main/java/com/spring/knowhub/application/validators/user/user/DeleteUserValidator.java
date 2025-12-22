package com.spring.knowhub.application.validators.user.user;

import com.spring.knowhub.application.commands.user.user.DeleteUserCommand;
import com.spring.knowhub.application.exceptions.user.user.DeleteUserException;

public class DeleteUserValidator {

    public static void validate(DeleteUserCommand command) {
        if (command == null) {
            throw DeleteUserException.missingRequiredField("command");
        }

        if (command.getUserId() == null || command.getUserId() <= 0) {
            throw DeleteUserException.missingRequiredField("userId");
        }
    }
}
