package com.spring.knowhub.application.validators.user.permission;

import com.spring.knowhub.application.commands.user.permission.DeletePermissionCommand;
import com.spring.knowhub.application.exceptions.user.permission.DeletePermissionException;

public class DeletePermissionValidator {

    public static void validate(DeletePermissionCommand command) {
        if (command == null) {
            throw DeletePermissionException.missingRequiredField("command");
        }

        if (command.getId() == null || command.getId() <= 0) {
            throw DeletePermissionException.invalidField("id");
        }
    }
}
