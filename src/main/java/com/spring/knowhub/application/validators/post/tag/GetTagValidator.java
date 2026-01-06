package com.spring.knowhub.application.validators.post.tag;

import com.spring.knowhub.application.exceptions.post.tag.GetTagException;
import com.spring.knowhub.application.queries.post.tag.GetTagByIdQuery;

public class GetTagValidator {
    public static void validate(GetTagByIdQuery query) {
        if (query.getId() == null ) {
            throw GetTagException.missingId();
        }
    }
}
