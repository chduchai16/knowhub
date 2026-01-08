package com.spring.knowhub.domain.specifications.post;


import com.spring.knowhub.domain.enums.post.PostStatus;
import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.domain.specifications.AlwaysTrueSpecification;
import com.spring.knowhub.domain.specifications.Specification;

public class PostSpecification {
    public static Specification<Post> hasStatus (String status) {
        return status == null
                ? new AlwaysTrueSpecification<>()
                : new PostHasStatusSpec(PostStatus.valueOf(status));
    }

    public static Specification<Post> hasKeyword (String keyword) {
        return (keyword == null || keyword.isBlank())
                ? new AlwaysTrueSpecification<>()
                : new PostHasContentSpec(keyword);
    }
}
