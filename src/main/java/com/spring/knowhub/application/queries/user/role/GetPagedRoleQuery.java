package com.spring.knowhub.application.queries.user.role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetPagedRoleQuery {
    private int page ;
    private int pageSize ;
}
