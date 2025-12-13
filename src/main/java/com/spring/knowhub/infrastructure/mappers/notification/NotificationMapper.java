package com.spring.knowhub.infrastructure.mappers.notification;

import com.spring.knowhub.domain.models.notification.Notification;
import com.spring.knowhub.infrastructure.entities.notification.NotificationEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationMapper {

    private final ModelMapper modelMapper ;
    private TypeMap<Notification , NotificationEntity> fromDomainToEntityTypeMap ;
    private TypeMap<NotificationEntity , Notification> fromEntityToDomainTypeMap ;

    public NotificationEntity fromDomainToEntity(Notification notification){
        if (fromDomainToEntityTypeMap == null) {
            fromDomainToEntityTypeMap = modelMapper.createTypeMap(Notification.class, NotificationEntity.class);
            fromDomainToEntityTypeMap.implicitMappings();
        }
        return fromDomainToEntityTypeMap.map(notification);
    }

    public Notification fromEntityToDomain(NotificationEntity notificationEntity){
        if (fromEntityToDomainTypeMap == null) {
            fromEntityToDomainTypeMap = modelMapper.createTypeMap(NotificationEntity.class, Notification.class);
            fromDomainToEntityTypeMap.implicitMappings();
        }
        return fromEntityToDomainTypeMap.map(notificationEntity);
    }
}
