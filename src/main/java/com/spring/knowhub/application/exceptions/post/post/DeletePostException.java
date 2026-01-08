package com.spring.knowhub.application.exceptions.post.post;

public class DeletePostException extends PostApplicationException{
    public DeletePostException (String message) {
        super("DELETE_POST_ERROR",message);
    }

    public DeletePostException (String message, Throwable cause) {
        super("DELETE_POST_ERROR",message,cause);
    }

    public static DeletePostException missingRequiredFields(String fieldName) {
        return new DeletePostException("Trường bắt buộc '" + fieldName + "' không được cung cấp");
    }

    public static DeletePostException objectNull (){
        return new DeletePostException("Command xóa Post không được null");
    }
}
