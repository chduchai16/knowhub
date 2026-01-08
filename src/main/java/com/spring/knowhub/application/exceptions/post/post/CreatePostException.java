package com.spring.knowhub.application.exceptions.post.post;

public class CreatePostException extends PostApplicationException{

    public CreatePostException (String message) {
        super("CREATE_POST_ERROR",message);
    }

    public CreatePostException (String message, Throwable cause) {
        super("CREATE_POST_ERROR",message,cause);
    }

    public static CreatePostException misingRequiredFields(String fieldName) {
        return new CreatePostException("Trường bắt buộc '" + fieldName + "' không được cung cấp");
    }

    public static CreatePostException objectNull (){
        return new CreatePostException("Command tạo Post không được null");
    }
}
