package com.spring.knowhub.application.exceptions.post.tag;

public class CreateTagException extends TagApplicationException{
    public CreateTagException (String message) {
        super("CREATE_TAG_ERROR",message);
    }

    public CreateTagException (String message, Throwable cause) {
        super("CREATE_TAG_ERROR",message,cause);
    }

    public static CreateTagException misingRequiredFields(String fieldName) {
        return new CreateTagException("Trường bắt buộc '" + fieldName + "' không được cung cấp");
    }
}
