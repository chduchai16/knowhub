package com.spring.knowhub.domain.exceptions.post.postmedia;

public class InvalidPostMediaException extends PostMediaDomainException{
    public InvalidPostMediaException(String message){
        super("INVALID_MEDIA_POST_ERROR" , message);
    }

    public InvalidPostMediaException(String message, Throwable cause){
        super("INVALID_MEDIA_POST_ERROR" , message, cause);
    }

    public static InvalidPostMediaException invalidMediaType (){
        return new InvalidPostMediaException("Kiểu phương tiện không hợp lệ. Vui lòng sử dụng 'IMAGE' hoặc 'VIDEO'.");
    }
}
