package com.spring.knowhub.domain.specifications.user;

import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.specifications.Specification;

public class UserHasRoleSpec implements Specification<User> {

    private final Long roleId ;

    public UserHasRoleSpec(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean isSatisfiedBy(User user) {
        return user.getRole().getId().equals(this.roleId) ;
    }

    public Long getRoleId() {
        return roleId;
    }
}
