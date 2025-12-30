package com.spring.knowhub.domain.specifications.user;

import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.specifications.Specification;

public class UserSearchSpec implements Specification<User> {

    private final String keyword ;

    public UserSearchSpec(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean isSatisfiedBy(User user) {
        if(keyword == null || keyword.isEmpty()) {
            return true;
        }
        return user.getUsername().contains(keyword) ||
               user.getEmail().contains(keyword) ||
                user.getFullName().contains(keyword);
    }

    public String getKeyword() {
        return keyword;
    }
}
