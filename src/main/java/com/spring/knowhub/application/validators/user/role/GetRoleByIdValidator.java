package com.spring.knowhub.application.validators.user.role;

import com.spring.knowhub.application.exceptions.user.role.GetRoleException;

public class GetRoleByIdValidator {
    public static void validate(Long roleId) {
        if (roleId == null || roleId <= 0) {
            throw GetRoleException.invalidField("roleId") ;
        }
    }
}
