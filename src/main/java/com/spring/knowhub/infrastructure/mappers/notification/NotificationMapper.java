package com.spring.knowhub.infrastructure.mappers.notification;

import com.spring.knowhub.domain.models.notification.Notification;
import com.spring.knowhub.infrastructure.entities.notification.NotificationEntity;
import com.spring.knowhub.infrastructure.entities.user.UserEntity;
import com.spring.knowhub.infrastructure.mappers.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationMapper {

    private final ModelMapper modelMapper;
    private final UserMapper userMapper;
    private TypeMap<Notification, NotificationEntity> fromDomainToEntityTypeMap;
    private TypeMap<NotificationEntity, Notification> fromEntityToDomainTypeMap;

    public NotificationEntity fromDomainToEntity(Notification notification) {
        if (notification == null)
            return null;

        if (fromDomainToEntityTypeMap == null) {
            fromDomainToEntityTypeMap = modelMapper.createTypeMap(Notification.class, NotificationEntity.class);
            fromDomainToEntityTypeMap.addMappings(mapper -> {
                mapper.skip(NotificationEntity::setActor);
            });
            fromDomainToEntityTypeMap.implicitMappings();
        }

        NotificationEntity entity = fromDomainToEntityTypeMap.map(notification);

        //map actor
        if (notification.getActor() != null) {
            if (notification.getActor().getId() != null) {
                UserEntity actorRef = new UserEntity();
                actorRef.setId(notification.getActor().getId());
                entity.setActor(actorRef);
            }
        }

        return entity;
    }

    public Notification fromEntityToDomain(NotificationEntity notificationEntity) {
        if (notificationEntity == null)
            return null;

        if (fromEntityToDomainTypeMap == null) {
            fromEntityToDomainTypeMap = modelMapper.createTypeMap(NotificationEntity.class, Notification.class);
            fromEntityToDomainTypeMap.addMappings(mapper -> {
                mapper.skip(Notification::setActor);
            });
            fromEntityToDomainTypeMap.implicitMappings();
        }

        Notification notification = fromEntityToDomainTypeMap.map(notificationEntity);

        // map actor
        if (notificationEntity.getActor() != null) {
            notification.setActor(userMapper.fromEntityToDomain(notificationEntity.getActor()));
        }

        return notification;
    }
}
