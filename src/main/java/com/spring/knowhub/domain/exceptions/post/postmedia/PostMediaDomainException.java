package com.spring.knowhub.domain.exceptions.post.postmedia;

public class PostMediaDomainException extends RuntimeException{

    private String errorCode ;

    public PostMediaDomainException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public PostMediaDomainException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
