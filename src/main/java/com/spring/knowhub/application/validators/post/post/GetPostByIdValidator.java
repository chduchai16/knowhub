package com.spring.knowhub.application.validators.post.post;

import com.spring.knowhub.application.exceptions.post.post.GetPostException;
import com.spring.knowhub.application.queries.post.post.GetPostByIdQuery;

public class GetPostByIdValidator {
    public static void validate(GetPostByIdQuery query) {
        if (query.getId() == null || query.getId() <= 0) {
            throw GetPostException.missingRequiredFields("id") ;
        }
    }
}
