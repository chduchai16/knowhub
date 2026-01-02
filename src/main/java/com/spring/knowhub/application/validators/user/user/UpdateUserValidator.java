package com.spring.knowhub.application.validators.user.user;

import com.spring.knowhub.application.commands.user.user.UpdateUserCommand;
import com.spring.knowhub.application.exceptions.user.user.UpdateUserException;
import com.spring.knowhub.domain.exceptions.user.user.InvalidUserException;

public class UpdateUserValidator {
    public static void validate(UpdateUserCommand command) {
        if (command == null) {
            throw UpdateUserException.missingRequiredField("command");
        }
        if (command.getId() == null || command.getId() <= 0) {
            throw UpdateUserException.missingRequiredField("userId");
        }
        if (command.getFullName() != null && command.getFullName().trim().isEmpty()) {
            throw InvalidUserException.emptyFullName();
        }
    }
}
