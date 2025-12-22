package com.spring.knowhub.application.validators.user.user;

import com.spring.knowhub.application.exceptions.user.user.GetUserException;
import com.spring.knowhub.application.queries.user.user.GetUserByIdQuery;

public class GetUserByIdValidator {

    public static void validate(GetUserByIdQuery query) {
        if (query == null) {
            throw GetUserException.missingRequiredField("query");
        }

        if (query.getUserId() == null || query.getUserId() <= 0) {
            throw GetUserException.missingRequiredField("userId");
        }
    }
}
