package com.spring.knowhub.application.exceptions.post.postlike;

public class PostLikeApplicationException extends RuntimeException {
    private String errorCode ;

    public PostLikeApplicationException(String errorCode , String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public PostLikeApplicationException(String errorCode , String message , Throwable cause) {
        super(message , cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage () {
        return super.getMessage() ;
    }
}
