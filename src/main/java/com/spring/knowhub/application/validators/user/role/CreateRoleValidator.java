package com.spring.knowhub.application.validators.user.role;

import com.spring.knowhub.application.exceptions.user.role.CreateRoleException;

public class CreateRoleValidator {
    public static void validate (String name) {
        if (name == null || name.trim().isEmpty()) {
            throw CreateRoleException.missingRequiredField("name");
        }
        if (name.length() < 3 || name.length() > 50) {
            throw CreateRoleException.invalidField("name" , "Độ dài phải từ 3 đến 50 ký tự");
        }
    }
}
