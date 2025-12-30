package com.spring.knowhub.domain.specifications.user;

import com.spring.knowhub.domain.enums.UserStatus;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.specifications.Specification;

public class UserHasStatusSpec implements Specification<User> {

    private final UserStatus status;

    public UserHasStatusSpec(UserStatus status) {
        this.status = status;
    }

    @Override
    public boolean isSatisfiedBy(User user) {
        return user.getStatus() == status;
    }
}
