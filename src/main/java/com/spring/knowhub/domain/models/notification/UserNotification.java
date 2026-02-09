package com.spring.knowhub.domain.models.notification;

import com.spring.knowhub.domain.models.BaseModel;
import com.spring.knowhub.domain.models.user.User;

import java.time.LocalDateTime;

public class UserNotification extends BaseModel {
    private Long id;
    private User user;
    private Notification notification;
    private Boolean isRead;
    private LocalDateTime readAt;

    public UserNotification() {
    }

    public UserNotification(Long id, User user, Notification notification, Boolean isRead, LocalDateTime readAt) {
        this.id = id;
        this.user = user;
        this.notification = notification;
        this.isRead = isRead;
        this.readAt = readAt;
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

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public LocalDateTime getReadAt() {
        return readAt;
    }

    public void setReadAt(LocalDateTime readAt) {
        this.readAt = readAt;
    }
}
