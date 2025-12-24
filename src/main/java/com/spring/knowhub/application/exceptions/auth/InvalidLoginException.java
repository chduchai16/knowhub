package com.spring.knowhub.application.exceptions.auth;

public class InvalidLoginException extends AuthApplicationException{

    public InvalidLoginException(String message) {
        super("INVALID_AUTH_EXCEPTION", message);
    }

    public InvalidLoginException(String message, Throwable cause) {
        super("INVALID_AUTH_EXCEPTION", message, cause);
    }

    public static InvalidLoginException missingRequiredFields(String fieldName ) {
        return new InvalidLoginException("Trường '" + fieldName + "' là bắt buộc và không được để trống.");
    }

    public static InvalidLoginException objectNull() {
        return new InvalidLoginException("Đối tượng LoginCommand không được phép là null.");
    }

    public static InvalidLoginException invalidFieldFormat(String fieldName, String details ) {
        return new InvalidLoginException("Định dạng của trường '" + fieldName + "' không hợp lệ. " + details);
    }

    public static InvalidLoginException authenticationFailed() {
        return new InvalidLoginException("Xác thực thất bại. Vui lòng kiểm tra lại tên đăng nhập và mật khẩu.");
    }

}
