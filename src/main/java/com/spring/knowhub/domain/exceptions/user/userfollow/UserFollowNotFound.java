package com.spring.knowhub.domain.exceptions.user.userfollow;

public class UserFollowNotFound extends UserFollowDomainException{ 
    public UserFollowNotFound(String message) {
        super("USER_FOLLOW_NOT_FOUND" ,message);
    }

    public UserFollowNotFound (String message ,Throwable cause) {
        super("USER_FOLLOW_NOT_FOUND" ,message , cause);
    }

    public static UserFollowNotFound byId(Long id) {
        return new UserFollowNotFound("Không tìm thấy user follow theo id: " + id);
    }

    public static UserFollowNotFound byUserAndFollower(String userName, String followerName) {
        return new UserFollowNotFound("Không tìm thấy user follow theo user name: " + userName + " và follower name: " + followerName);
    }
}

