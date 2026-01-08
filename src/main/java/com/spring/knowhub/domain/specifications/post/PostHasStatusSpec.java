package com.spring.knowhub.domain.specifications.post;

import com.spring.knowhub.domain.enums.post.PostStatus;
import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.domain.specifications.Specification;

public class PostHasStatusSpec implements Specification<Post> {

    private final PostStatus status ;

    public PostHasStatusSpec(PostStatus status) {
        this.status = status;
    }

    @Override
    public boolean isSatisfiedBy(Post post) {
        if(status == null) {
            return true;
        }
        return post.getStatus() == status;
    }

    public PostStatus getStatus() {
        return status;
    }
}
