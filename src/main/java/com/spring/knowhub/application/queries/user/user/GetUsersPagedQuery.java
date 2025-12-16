package com.spring.knowhub.application.queries.user.user;

import com.spring.knowhub.application.buses.Query;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
public class GetUsersPagedQuery implements Query<Object> {
    private Pageable pageable;
}
