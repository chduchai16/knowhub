package com.spring.knowhub.application.validators.user.role;

import com.spring.knowhub.application.exceptions.user.role.DeleteRoleException;

public class DeleteRoleValidator {
    public static void validate(Long roleId) {
        if (roleId <= 0) {
            throw DeleteRoleException.invalidField("roleId", "Phải là số nguyên dương lớn hơn 0");
        }
    }
}
