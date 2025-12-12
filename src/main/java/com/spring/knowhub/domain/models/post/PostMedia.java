package com.spring.knowhub.domain.models.post;

import com.spring.knowhub.domain.enums.MediaType;

public class PostMedia {
    private Long id ;
    private Post post ;
    private String url ;
    private MediaType type ;

    public PostMedia() {}

    public PostMedia(Long id, Post post, String url, MediaType type) {
        this.id = id;
        this.post = post;
        this.url = url;
        this.type = type;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }
}
