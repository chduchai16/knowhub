package com.spring.knowhub.domain.models.bookmark;

import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.domain.models.user.User;

public class Bookmark {
    private Long id ;
    private User user ;
    private Post post ;

    public Bookmark(){}

    public Bookmark(Long id, User user, Post post) {
        this.id = id;
        this.user = user;
        this.post = post;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
