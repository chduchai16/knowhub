package com.spring.knowhub.domain.models.post;

public class PostTag {
    private Long id ;
    private Post post;
    private Tag tag ;

    public PostTag() { }

    public PostTag(Long id, Post post, Tag tag) {
        this.id = id;
        this.post = post;
        this.tag = tag;
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

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
