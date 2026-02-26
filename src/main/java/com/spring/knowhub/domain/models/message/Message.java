package com.spring.knowhub.domain.models.message;

import com.spring.knowhub.domain.models.BaseModel;
import com.spring.knowhub.domain.models.media.Media;
import com.spring.knowhub.domain.models.user.User;
import java.time.LocalDateTime;
import java.util.List;

public class Message extends BaseModel {
    private Long id;
    private User sender;
    private User receiver;
    private String content;
    private List<Media> media;
    private Boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Message() {
    }

    public Message(Long id, User sender, User receiver, String content, List<Media> media, Boolean isDeleted) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.media = media;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
