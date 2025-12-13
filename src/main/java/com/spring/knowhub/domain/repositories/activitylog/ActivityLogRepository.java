package com.spring.knowhub.domain.repositories.activitylog;

import com.spring.knowhub.domain.models.activitylog.ActivityLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ActivityLogRepository {
    Optional<ActivityLog> save(ActivityLog activityLog);
    void deleteById(Long id);
    Optional<ActivityLog> findById(Long id);
    Page<ActivityLog> findActivityLogsPaged(Pageable pageable);
}
