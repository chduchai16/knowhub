package com.spring.knowhub.domain.repositories.activitylog;

import com.spring.knowhub.domain.models.activitylog.ActivityLog;

import java.util.Optional;

public interface ActivityLogCommandRepository {
    Optional<ActivityLog> save(ActivityLog activityLog);
    void deleteById(Long id);
}
