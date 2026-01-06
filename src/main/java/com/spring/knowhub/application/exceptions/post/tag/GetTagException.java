package com.spring.knowhub.application.exceptions.post.tag;

public class GetTagException extends TagApplicationException{
    public GetTagException (String message) {
        super("GET_TAG_ERROR" ,message);
    }

    public GetTagException (String message, Throwable cause) {
        super("GET_TAG_ERROR" ,message, cause);
    }

    public static GetTagException missingId() {
        return new GetTagException("Thiếu tham số id để lấy Tag");
    }
}
