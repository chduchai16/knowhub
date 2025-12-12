package com.spring.knowhub.domain.models.user;

public class UserFollow {
    private Long id ;
    private User user ;
    private User follower ;

    public UserFollow() {}

    public UserFollow(Long id, User user, User follower) {
        this.id = id;
        this.user = user;
        this.follower = follower;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }
}
