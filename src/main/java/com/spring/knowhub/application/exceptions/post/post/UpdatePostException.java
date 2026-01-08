package com.spring.knowhub.application.exceptions.post.post;

public class UpdatePostException extends PostApplicationException{
    public UpdatePostException (String message) {
        super("UPDATE_POST_ERROR",message);
    }

    public UpdatePostException (String message, Throwable cause) {
        super("UPDATE_POST_ERROR",message,cause);
    }

    public static UpdatePostException misingRequiredFields(String fieldName) {
        return new UpdatePostException("Trường bắt buộc '" + fieldName + "' không được cung cấp");
    }

    public static UpdatePostException objectNull (){
        return new UpdatePostException("Command cập nhật Post không được null");
    }
}
