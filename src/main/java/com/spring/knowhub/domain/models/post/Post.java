package com.spring.knowhub.domain.models.post;

import com.spring.knowhub.domain.enums.post.PostStatus;
import com.spring.knowhub.domain.enums.post.Privacy;
import com.spring.knowhub.domain.models.BaseModel;
import com.spring.knowhub.domain.models.media.Media;
import com.spring.knowhub.domain.models.user.User;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Post extends BaseModel {
    private Long id;
    private User user;
    private String content;
    private Privacy privacy;
    private List<Media> media;
    private PostStatus status;
    private Set<PostTag> postTags = new HashSet<>();
    private Long likeQuantity;
    private Boolean isLiked;
    private Long postLikeId;

    public Post() {
    }

    public Post(Long id, User user, String content, Privacy privacy, PostStatus status, List<Media> media,
            Set<PostTag> postTags) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.privacy = privacy;
        this.media = media;
        this.status = status;
        this.postTags = postTags;
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

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    public Set<PostTag> getPostTags() {
        return postTags;
    }

    public void setPostTags(Set<PostTag> postTags) {
        this.postTags = postTags;
    }

    public Long getLikeQuantity() {
        return likeQuantity;
    }

    public void setLikeQuantity(Long likeQuantity) {
        this.likeQuantity = likeQuantity;
    }

    public Boolean getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(Boolean isLiked) {
        this.isLiked = isLiked;
    }

    public Long getPostLikeId() {
        return postLikeId;
    }

    public void setPostLikeId(Long postLikeId) {
        this.postLikeId = postLikeId;
    }
}
