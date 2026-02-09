package com.spring.knowhub.application.queries.notification;

import com.spring.knowhub.application.buses.Query;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetUnreadCountQuery implements Query<Long> {
    private Long userId;
}
