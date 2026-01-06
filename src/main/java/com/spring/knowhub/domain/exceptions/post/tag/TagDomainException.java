package com.spring.knowhub.domain.exceptions.post.tag;

public class TagDomainException extends RuntimeException {
    private String errorCode ;

    public TagDomainException (String errorCode , String message) {
        super(message);
        this.errorCode = errorCode ;
    }

    public TagDomainException (String errorCode , String message , Throwable cause) {
        super(message , cause);
        this.errorCode = errorCode ;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
