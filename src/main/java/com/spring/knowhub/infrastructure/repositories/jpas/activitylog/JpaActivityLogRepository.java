package com.spring.knowhub.infrastructure.repositories.jpas.activitylog;

import com.spring.knowhub.infrastructure.entities.activitylog.ActivityLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaActivityLogRepository extends JpaRepository<ActivityLogEntity , Long> {
}
