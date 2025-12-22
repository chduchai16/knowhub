package com.spring.knowhub.application.validators.user.permission;

import com.spring.knowhub.application.exceptions.user.permission.GetPermissionException;
import com.spring.knowhub.application.queries.user.permission.GetPermissionByIdQuery;

public class GetPermissionByIdValidator {
    public static void validate(GetPermissionByIdQuery query) {
        if (query == null) {
            throw GetPermissionException.missingRequiredField("query");
        }

        if (query.getId() == null || query.getId() <= 0) {
            throw GetPermissionException.invalidField("id");
        }
    }
}
