package com.spring.knowhub.application.exceptions.media;

public class CreateMediaException extends MediaApplicationException{
    public CreateMediaException(String message) {
        super("CREATE_MEDIA_ERROR",message);
    }

    public CreateMediaException (String message, Throwable cause) {
        super("CREATE_MEDIA_ERROR",message, cause);
    }

    public static CreateMediaException objectNull() {
        return new CreateMediaException("Đối tượng Media không được phép là null.");
    }
}
