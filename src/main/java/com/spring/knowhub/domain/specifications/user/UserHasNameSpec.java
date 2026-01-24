package com.spring.knowhub.domain.specifications.user;

import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.specifications.Specification;

public class UserHasNameSpec implements Specification<User> {

    private String name;

    public UserHasNameSpec(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isSatisfiedBy(User user) {
        return user.getFullName().contains(this.name) || user.getUsername().contains(this.name);
    }
}
