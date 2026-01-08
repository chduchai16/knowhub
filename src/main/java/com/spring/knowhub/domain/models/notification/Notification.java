package com.spring.knowhub.domain.models.notification;

import com.spring.knowhub.domain.enums.notification.NotificationType;

public class Notification {
    private Long id ;
    private NotificationType type ;
    private String title ;
    private String content ;

    public Notification() {}

    public Notification(Long id, NotificationType type, String title, String content) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
