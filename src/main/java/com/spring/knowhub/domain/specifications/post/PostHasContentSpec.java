package com.spring.knowhub.domain.specifications.post;

import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.domain.specifications.Specification;

public class PostHasContentSpec implements Specification<Post> {

    private String keyword ;

    public PostHasContentSpec(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean isSatisfiedBy(Post post) {
        if(keyword == null || keyword.isEmpty()) {
            return true;
        }
        return post.getContent().contains(keyword);
    }

    @Override
    public Specification<Post> and(Specification<Post> other) {
        return Specification.super.and(other);
    }

    public String getKeyword() {
        return keyword;
    }
}
