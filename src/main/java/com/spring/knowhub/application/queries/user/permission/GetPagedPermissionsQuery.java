package com.spring.knowhub.application.queries.user.permission;

import com.spring.knowhub.application.buses.Query;
import com.spring.knowhub.domain.models.user.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
public class GetPagedPermissionsQuery implements Query<Page<Permission>> {
    private int page;
    private int size;
}
