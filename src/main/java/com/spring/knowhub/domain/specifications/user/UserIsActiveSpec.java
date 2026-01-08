package com.spring.knowhub.domain.specifications.user;

import com.spring.knowhub.domain.enums.user.UserStatus;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.specifications.Specification;

public class UserIsActiveSpec implements Specification<User> {

    @Override
    public boolean isSatisfiedBy(User user) {
        return user.getStatus() == UserStatus.ACTIVE;
    }
}
