package com.spring.knowhub.presentation.exceptions.user;

public class UserResponseMappingException extends UserPresentationException{

    public UserResponseMappingException(String message) {
        super("USER_RESPONSE_MAPPING_ERROR", message);
    }

    public UserResponseMappingException(String message, Throwable cause) {
        super("USER_RESPONSE_MAPPING_ERROR", message, cause);
    }

    public static UserResponseMappingException roleResponseMappingError(Long roleId) {
        return new UserResponseMappingException("Lỗi ánh xạ dữ liệu phản hồi cho vai trò với ID '" + roleId + "'");
    }

    public static UserResponseMappingException objectNull(){
        return new UserResponseMappingException("Đối tượng để ánh xạ dữ liệu phản hồi không được phép là null");
    }

    public static UserResponseMappingException errorMapping(Throwable cause){
        return new UserResponseMappingException("Lỗi xảy ra trong quá trình ánh xạ dữ liệu phản hồi", cause);
    }

}
