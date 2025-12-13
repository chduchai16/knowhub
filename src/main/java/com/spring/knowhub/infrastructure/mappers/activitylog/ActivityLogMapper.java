package com.spring.knowhub.infrastructure.mappers.activitylog;

import com.spring.knowhub.domain.models.activitylog.ActivityLog;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.infrastructure.entities.activitylog.ActivityLogEntity;
import com.spring.knowhub.infrastructure.entities.user.UserEntity;
import com.spring.knowhub.infrastructure.mappers.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActivityLogMapper {

    private final ModelMapper modelMapper ;
    private final UserMapper userMapper ;
    private TypeMap <ActivityLog , ActivityLogEntity> fromDomainToEntityTypeMap ;
    private TypeMap <ActivityLogEntity , ActivityLog> fromEntityToDomainTypeMap ;

    public ActivityLog fromEntityToDomain(ActivityLogEntity activityLogEntity) {
        if(activityLogEntity == null) return null ;
        if(fromEntityToDomainTypeMap == null) {
            fromEntityToDomainTypeMap = modelMapper.createTypeMap(ActivityLogEntity.class, ActivityLog.class);
            fromEntityToDomainTypeMap.getMappings().clear();
            fromEntityToDomainTypeMap.addMappings(mapper -> {
                mapper.skip(ActivityLog::setUser);
            });
            fromEntityToDomainTypeMap.implicitMappings();
        }

        ActivityLog activityLog = fromEntityToDomainTypeMap.map(activityLogEntity) ;

        // thực hiện map thủ công user
        if(activityLogEntity.getUser() != null) {
            User user = userMapper.fromEntityToDomain(activityLogEntity.getUser());
            activityLog.setUser(user);
        }
        return activityLog ;
    }

    public ActivityLogEntity fromDomainToEntity(ActivityLog activityLog) {
        if(activityLog == null) return null ;
        if(fromDomainToEntityTypeMap == null) {
            fromDomainToEntityTypeMap = modelMapper.createTypeMap(ActivityLog.class, ActivityLogEntity.class);
            fromDomainToEntityTypeMap.getMappings().clear();
            fromDomainToEntityTypeMap.addMappings(mapper -> {
                mapper.skip(ActivityLogEntity::setUser);
            });
            fromDomainToEntityTypeMap.implicitMappings();
        }

        ActivityLogEntity activityLogEntity = fromDomainToEntityTypeMap.map(activityLog) ;

        // thực hiện map thủ công user
        if(activityLog.getUser() != null) {
            UserEntity userEntity = userMapper.fromDomainToEntity(activityLog.getUser());
            activityLogEntity.setUser(userEntity);
        }
        return activityLogEntity ;
    }

}
