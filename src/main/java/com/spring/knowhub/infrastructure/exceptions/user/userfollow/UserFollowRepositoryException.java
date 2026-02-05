package com.spring.knowhub.infrastructure.exceptions.user.userfollow;

public class UserFollowRepositoryException extends UserFollowInfrastructureExcepiton {
    
    public UserFollowRepositoryException(String message) {
        super("USER_FOLLOW_REPOSITORY_ERROR" ,message);
    }
    
    public UserFollowRepositoryException(String message, Throwable cause) {
        super("USER_FOLLOW_REPOSITORY_ERROR", message, cause);
    }
    
    public static UserFollowRepositoryException saveFailed(String details) {
        return new UserFollowRepositoryException("Lỗi khi lưu UserFollow vào database: " + details);
    }

    public static UserFollowRepositoryException deleteFailed(String details) {
        return new UserFollowRepositoryException("Lỗi khi xóa UserFollow: " + details);
    }

    public static UserFollowRepositoryException findFailed(String details) {
        return new UserFollowRepositoryException("Lỗi khi tìm kiếm UserFollow: " + details);
    }
}
