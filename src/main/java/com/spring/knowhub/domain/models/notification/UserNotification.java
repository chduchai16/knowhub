package com.spring.knowhub.domain.models.notification;

import com.spring.knowhub.domain.models.user.User;

public class UserNotification {
    private Long id;
    private User user ;
    private Notification notification ;
    private Boolean isRead ;

    public UserNotification() {}

    public UserNotification(Long id, User user, Notification notification, Boolean isRead) {
        this.id = id;
        this.user = user;
        this.notification = notification;
        this.isRead = isRead;
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

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }
}
