package com.spring.knowhub.application.queries.user.user;

import com.spring.knowhub.application.buses.Query;
import com.spring.knowhub.domain.models.user.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class GetUsersByNameQuery implements Query<List<User>> {
    private String name;
    private Pageable pageable ;

    public GetUsersByNameQuery(String name , Pageable pageable) {
        this.name = name;
        this.pageable = pageable ;
    }

    public String getName() {
        return name;
    }

    public Pageable getPageable() {
        return pageable;
    }
}
