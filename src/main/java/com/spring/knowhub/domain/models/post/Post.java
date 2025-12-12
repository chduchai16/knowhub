package com.spring.knowhub.domain.models.post;

import com.spring.knowhub.domain.enums.Privacy;
import com.spring.knowhub.domain.models.user.User;

import java.util.List;

public class Post {
    private Long id ;
    private User user ;
    private String content ;
    private Privacy privacy ;
    private  Boolean isDeleted ;
    private List<PostMedia> media ;

    public Post(){}

    public Post(Long id, User user, String content, Privacy privacy, Boolean isDeleted, List<PostMedia> media) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.privacy = privacy;
        this.isDeleted = isDeleted;
        this.media = media;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public List<PostMedia> getMedia() {
        return media;
    }

    public void setMedia(List<PostMedia> media) {
        this.media = media;
    }
}
