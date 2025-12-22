package com.spring.knowhub.application.queries.user.role;

import com.spring.knowhub.application.buses.Query;
import com.spring.knowhub.domain.models.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetRoleByIdQuery implements Query<Role> {
    private Long roleId ;
}
