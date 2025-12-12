package com.spring.knowhub.domain.models.activitylog;

import com.spring.knowhub.domain.models.user.User;

public class ActivityLog {
    private Long id ;
    private User user ;
    private String action ;
    private String targetType ;
    private Long targetId ;
    private String metadata ;

    public ActivityLog() {}

    public ActivityLog(Long id, User user, String action, String targetType, Long targetId, String metadata) {
        this.id = id;
        this.user = user;
        this.action = action;
        this.targetType = targetType;
        this.targetId = targetId;
        this.metadata = metadata;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }
}
