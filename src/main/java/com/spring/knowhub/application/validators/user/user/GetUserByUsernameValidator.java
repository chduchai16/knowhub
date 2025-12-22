package com.spring.knowhub.application.validators.user.user;

import com.spring.knowhub.application.exceptions.user.user.GetUserException;
import com.spring.knowhub.application.queries.user.user.GetUserByUsernameQuery;

public class GetUserByUsernameValidator {
    public static void validate(GetUserByUsernameQuery query) {
        if (query == null) {
            throw GetUserException.missingRequiredField("query");
        }

        if (query.getUsername() == null || query.getUsername().trim().isEmpty()) {
            throw GetUserException.missingRequiredField("username");
        }
    }

}
