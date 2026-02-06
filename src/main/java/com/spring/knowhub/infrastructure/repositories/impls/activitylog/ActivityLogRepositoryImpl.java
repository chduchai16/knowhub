package com.spring.knowhub.infrastructure.repositories.impls.activitylog;

import com.spring.knowhub.domain.models.activitylog.ActivityLog;
import com.spring.knowhub.domain.repositories.activitylog.ActivityLogRepository;
import com.spring.knowhub.infrastructure.entities.activitylog.ActivityLogEntity;
import com.spring.knowhub.infrastructure.mappers.activitylog.ActivityLogMapper;
import com.spring.knowhub.infrastructure.repositories.jpas.activitylog.JpaActivityLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ActivityLogRepositoryImpl implements ActivityLogRepository {

    private final JpaActivityLogRepository jpaActivityLogRepository;
    private final ActivityLogMapper activityLogMapper;

    @Override
    public ActivityLog save(ActivityLog activityLog) {
        ActivityLogEntity activityLogEntity = activityLogMapper.fromDomainToEntity(activityLog);
        ActivityLogEntity savedEntity = jpaActivityLogRepository.save(activityLogEntity);
        return activityLogMapper.fromEntityToDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
    }

    @Override
    public Optional<ActivityLog> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<ActivityLog> findActivityLogsPaged(Pageable pageable) {
        return null;
    }
}
