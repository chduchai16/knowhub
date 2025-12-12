package com.spring.knowhub.domain.models.post;

import com.spring.knowhub.domain.models.user.User;

public class PostLike {
    private Long id ;
    private Post post ;
    private User user ;

    public PostLike(){}

    public PostLike(Long id, Post post, User user) {
        this.id = id;
        this.post = post;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
