package com.spring.knowhub.infrastructure.mappers.notification;

import com.spring.knowhub.domain.models.notification.UserNotification;
import com.spring.knowhub.infrastructure.entities.notification.UserNotificationEntity;
import com.spring.knowhub.infrastructure.mappers.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserNotificationMapper {

    private final ModelMapper modelMapper ;
    private final NotificationMapper notificationMapper ;
    private final UserMapper userMapper ;
    private TypeMap<UserNotification , UserNotificationEntity> fromDomainToEntityTypeMap ;
    private TypeMap<UserNotificationEntity , UserNotification> fromEntityToDomainTypeMap ;

    public UserNotificationEntity fromDomainToEntity(UserNotification userNotification){
        if (fromDomainToEntityTypeMap == null) {
            fromDomainToEntityTypeMap = modelMapper.createTypeMap(UserNotification.class, UserNotificationEntity.class);
            fromDomainToEntityTypeMap.getMappings().clear();
            fromDomainToEntityTypeMap.addMappings(mapper -> {
                mapper.skip(UserNotificationEntity::setUser);
                mapper.skip(UserNotificationEntity::setNotification);
            }) ;
            fromDomainToEntityTypeMap.implicitMappings();
        }

        UserNotificationEntity userNotificationEntity = fromDomainToEntityTypeMap.map(userNotification);

        // map user
        if (userNotification.getUser() != null) {
            userNotificationEntity.setUser(this.userMapper.fromDomainToEntity(userNotification.getUser()));
        }

        // map notificaion
        if(userNotification.getNotification()!= null) {
            userNotificationEntity.setNotification(this.notificationMapper.fromDomainToEntity(userNotification.getNotification()));
        }

        return userNotificationEntity ;
    }

    public UserNotification fromEntityToDomain(UserNotificationEntity userNotificationEntity){
        if (fromEntityToDomainTypeMap == null) {
            fromEntityToDomainTypeMap = modelMapper.createTypeMap(UserNotificationEntity.class, UserNotification.class);
            fromEntityToDomainTypeMap.getMappings().clear();
            fromEntityToDomainTypeMap.addMappings(mapper -> {
                mapper.skip(UserNotification::setUser);
                mapper.skip(UserNotification::setNotification);
            }) ;
            fromEntityToDomainTypeMap.implicitMappings();
        }

        UserNotification userNotification = fromEntityToDomainTypeMap.map(userNotificationEntity);

        // map user
        if (userNotificationEntity.getUser() != null) {
            userNotification.setUser(this.userMapper.fromEntityToDomain(userNotificationEntity.getUser()));
        }

        // map notificaion
        if(userNotificationEntity.getNotification()!= null) {
            userNotification.setNotification(this.notificationMapper.fromEntityToDomain(userNotificationEntity.getNotification()));
        }

        return userNotification ;
    }

}
