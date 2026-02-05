package com.spring.knowhub.infrastructure.exceptions.user.userfollow;

public class UserFollowMappingException extends UserFollowInfrastructureExcepiton{

    public UserFollowMappingException(String message) {
        super("USER_FOLLOW_MAPPING_ERROR", message);
    }

    public UserFollowMappingException(String message, Throwable cause) {
        super("USER_FOLLOW_MAPPING_ERROR", message, cause);
    }

    public static UserFollowMappingException entityToDomainMappingFailed(String details) {
        return new UserFollowMappingException("Lỗi khi map UserFollow Entity sang UserFollow domain: " + details);
    }

    public static UserFollowMappingException domainToEntityMappingFailed(String details) {
        return new UserFollowMappingException("Lỗi khi map UserFollow domain sang UserFollow Entity: " + details);
    }
     
}
