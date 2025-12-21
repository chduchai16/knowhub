package com.spring.knowhub.application.queries.user.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.knowhub.application.buses.Query;
import com.spring.knowhub.domain.models.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUsersPagedQuery implements Query<Page<User>> {
    private Pageable pageable;
}
