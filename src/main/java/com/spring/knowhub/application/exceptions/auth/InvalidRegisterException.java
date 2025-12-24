package com.spring.knowhub.application.exceptions.auth;

public class InvalidRegisterException extends AuthApplicationException{
    public InvalidRegisterException(String message) {
        super("INVALID_REGISTER_ERROR", message);
    }

    public InvalidRegisterException(String message, Throwable cause) {
        super("INVALID_REGISTER_ERROR", message, cause);
    }

    public static InvalidRegisterException missingRequiredFields(String fieldName ) {
        return new InvalidRegisterException("Trường '" + fieldName + "' là bắt buộc và không được để trống.");
    }

    public static InvalidRegisterException objectNull() {
        return new InvalidRegisterException("Đối tượng RegisterCommand không được phép là null.");
    }

    public static InvalidRegisterException invalidFieldFormat(String fieldName, String details ) {
        return new InvalidRegisterException("Định dạng của trường '" + fieldName + "' không hợp lệ. " + details);
    }

}
