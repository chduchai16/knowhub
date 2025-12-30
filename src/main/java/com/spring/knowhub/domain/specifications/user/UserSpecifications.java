package com.spring.knowhub.domain.specifications.user;

import com.spring.knowhub.domain.enums.UserStatus;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.specifications.AlwaysTrueSpecification;
import com.spring.knowhub.domain.specifications.Specification;

public class UserSpecifications {

    public static Specification<User> isActiveUser() {
        return new UserIsActiveSpec();
    }

    public static Specification<User> hasRole(Long roleId) {
        return roleId == null
                ? new AlwaysTrueSpecification<>()
                : new UserHasRoleSpec(roleId);
    }

    public static Specification<User> hasKeyword(String keyword) {
        return (keyword == null || keyword.isBlank())
                ? new AlwaysTrueSpecification<>()
                : new UserSearchSpec(keyword);
    }

    public static Specification<User> hasStatus(UserStatus status) {
        return status == null
                ? new AlwaysTrueSpecification<>()
                : new UserHasStatusSpec(status);
    }
}

