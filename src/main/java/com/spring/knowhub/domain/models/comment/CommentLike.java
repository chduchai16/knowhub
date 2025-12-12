package com.spring.knowhub.domain.models.comment;

import com.spring.knowhub.domain.models.user.User;

public class CommentLike {
    private Long id ;
    private Comment comment ;
    private User user ;

    public CommentLike(){}

    public CommentLike(Long id, Comment comment, User user) {
        this.id = id;
        this.comment = comment;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
