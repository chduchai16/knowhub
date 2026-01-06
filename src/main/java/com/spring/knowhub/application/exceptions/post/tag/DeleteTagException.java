package com.spring.knowhub.application.exceptions.post.tag;

public class DeleteTagException extends TagApplicationException{

    public DeleteTagException(String message) {
        super("DELETE_TAG_ERROR",message);
    }

    public DeleteTagException(String message, Throwable cause) {
        super("DELETE_TAG_ERROR",message, cause);
    }

    public static DeleteTagException misingRequiredField(String fieldName) {
        return new DeleteTagException("Trường bắt buộc" + fieldName + " bị thiếu.");
    }
}
