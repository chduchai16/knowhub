package com.spring.knowhub.domain.specifications.post;

import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.domain.specifications.Specification;

public class PostHasUserNameSpec implements Specification<Post> {
    private final String userName ;

    public PostHasUserNameSpec(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean isSatisfiedBy(Post post) {
        if(userName == null || userName.isEmpty()) {
            return true;
        }
        return post.getUser().getUsername().equalsIgnoreCase(userName);
    }


    public String getUserName() {
        return userName;
    }
}
