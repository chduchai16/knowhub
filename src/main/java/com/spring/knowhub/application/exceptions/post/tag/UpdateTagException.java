package com.spring.knowhub.application.exceptions.post.tag;

public class UpdateTagException extends TagApplicationException{

    public UpdateTagException(String message) {
        super("UPDATE_TAG_ERROR",message);
    }

    public UpdateTagException(String message, Throwable cause) {
        super("UPDATE_TAG_ERROR",message, cause);
    }

    public static UpdateTagException missingRequiredFields(String fieldName) {
        return new UpdateTagException("Trường bắt buộc" + fieldName + " bị thiếu.");
    }

}

