package com.spring.knowhub.application.exceptions.post.post;

public class GetPostException extends PostApplicationException{
    public GetPostException (String message) {
        super("GET_POST_ERROR",message);
    }

    public GetPostException (String message, Throwable cause) {
        super("GET_POST_ERROR",message,cause);
    }

    public static GetPostException missingRequiredFields(String fieldName) {
        return new GetPostException("Trường bắt buộc '" + fieldName + "' không được cung cấp");
    }

    public static GetPostException objectNull (){
        return new GetPostException("Command lấy Post không được null");
    }
}
