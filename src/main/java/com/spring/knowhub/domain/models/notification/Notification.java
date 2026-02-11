package com.spring.knowhub.domain.models.notification;

import com.spring.knowhub.domain.enums.notification.NotificationType;
import com.spring.knowhub.domain.models.BaseModel;
import com.spring.knowhub.domain.models.user.User;

public class Notification extends BaseModel {
    private Long id;
    private NotificationType type;
    private String title;
    private String content;
    private User actor; // người thực hiện
    private Long referenceId; // id được thực hiện
    private String referenceType;
    private Long postId;

    public Notification() {
    }

    public Notification(Long id, NotificationType type, String title, String content,
            User actor, Long referenceId, String referenceType, Long postId) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.content = content;
        this.actor = actor;
        this.referenceId = referenceId;
        this.referenceType = referenceType;
        this.postId = postId;
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

    public User getActor() {
        return actor;
    }

    public void setActor(User actor) {
        this.actor = actor;
    }

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
