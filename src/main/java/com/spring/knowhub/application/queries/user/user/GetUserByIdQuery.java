package com.spring.knowhub.application.queries.user.user;

import com.spring.knowhub.application.buses.Query;
import com.spring.knowhub.domain.models.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserByIdQuery implements Query<User> {
    private Long userId;
}
