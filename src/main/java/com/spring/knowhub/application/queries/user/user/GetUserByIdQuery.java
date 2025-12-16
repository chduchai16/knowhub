package com.spring.knowhub.application.queries.user.user;

import com.spring.knowhub.application.buses.Query;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class  GetUserByIdQuery implements Query<Object> {
    private Long userId;
}
