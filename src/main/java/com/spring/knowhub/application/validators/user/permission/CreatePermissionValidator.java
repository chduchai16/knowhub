package com.spring.knowhub.application.validators.user.permission;

import com.spring.knowhub.application.commands.user.permission.CreatePermissionCommand;
import com.spring.knowhub.application.exceptions.user.permission.CreatePermissionException;
import com.spring.knowhub.domain.exceptions.user.permission.InvalidPermissionException;

public class CreatePermissionValidator {

    public static void validate(CreatePermissionCommand command) {
        if (command == null) {
            throw CreatePermissionException.missingRequiredField("command");
        }

        if (command.getCode() == null || command.getCode().trim().isEmpty()) {
            throw CreatePermissionException.invalidCode("Mã quyền không được để trống");
        }
    }
}
