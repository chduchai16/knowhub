package com.spring.knowhub.application.exceptions.user.user;

public class GetUserException extends UserApplicationException{

    public GetUserException (String message) {
        super("GET_USER_QUERY_FAILED", message);
    }

    public GetUserException (String message, Throwable cause) {
        super("GET_USER_QUERY_FAILED", message, cause);
    }

    public static GetUserException missingRequiredField(String fieldName) {
        return new GetUserException("Trường bắt buộc '" + fieldName + "' bị thiếu");
    }

    public static GetUserException invalidField(String fieldName , String details) {
        return new GetUserException("Trường '" + fieldName + "' không hợp lệ: " + details);
    }
}
