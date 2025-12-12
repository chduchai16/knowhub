package com.spring.knowhub.domain.models.comment;

import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.domain.models.user.User;

public class Comment {

    private Long id;
    private Post post ;
    private User user ;
    private Comment parent;
    private String content ;

    public Comment() {}

    public Comment(Long id, Post post, User user, Comment parent, String content) {
        this.id = id;
        this.post = post;
        this.user = user;
        this.parent = parent;
        this.content = content;
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

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
