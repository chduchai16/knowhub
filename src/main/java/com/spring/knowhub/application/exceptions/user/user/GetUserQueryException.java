package com.spring.knowhub.application.exceptions.user.user;


public class GetUserQueryException extends UserApplicationException {
    public GetUserQueryException(String message) {
        super("GET_USER_QUERY_FAILED", message);
    }

    public GetUserQueryException(String message, Throwable cause) {
        super("GET_USER_QUERY_FAILED", message, cause);
    }

    public static GetUserQueryException missingRequiredField(String fieldName) {
        return new GetUserQueryException("Trường bắt buộc '" + fieldName + "' bị thiếu");
    }
}

