package com.spring.knowhub.application.validators.user.permission;

import com.spring.knowhub.application.commands.user.permission.UpdatePermissionCommand;
import com.spring.knowhub.application.exceptions.user.permission.UpdatePermissionException;

public class UpdatePermissionValidator {
    public static void validate(UpdatePermissionCommand command) {
        if (command == null) {
            throw UpdatePermissionException.missingRequiredField("command");
        }

        if (command.getId() == null || command.getId() <= 0) {
            throw UpdatePermissionException.invalidField("id");
        }

        if (command.getCode() == null || command.getCode().trim().isEmpty()) {
            throw UpdatePermissionException.missingRequiredField("code");
        }
    }
}
