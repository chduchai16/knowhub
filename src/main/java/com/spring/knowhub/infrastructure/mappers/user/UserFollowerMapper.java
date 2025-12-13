package com.spring.knowhub.infrastructure.mappers.user;

import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.models.user.UserFollow;
import com.spring.knowhub.infrastructure.entities.user.UserEntity;
import com.spring.knowhub.infrastructure.entities.user.UserFollowerEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFollowerMapper {

    private final ModelMapper modelMapper ;
    private final UserMapper userMapper ;
    private TypeMap<UserFollow , UserFollowerEntity> fromDomainToEntityTypeMap ;
    private TypeMap<UserFollowerEntity , UserFollow> fromEntityToDomainTypeMap ;

    public UserFollowerEntity fromDomainToEntity(UserFollow userFollow) {
        if(userFollow == null) return null ;
        if(fromDomainToEntityTypeMap == null) {
            fromDomainToEntityTypeMap = modelMapper.createTypeMap(UserFollow.class, UserFollowerEntity.class);
            fromDomainToEntityTypeMap.getMappings().clear();
            fromDomainToEntityTypeMap.addMappings(mapper -> {
                mapper.skip(UserFollowerEntity::setUser);
                mapper.skip(UserFollowerEntity::setFollower);
            });
            fromDomainToEntityTypeMap.implicitMappings();
        }

        UserFollowerEntity userFollowerEntity = fromDomainToEntityTypeMap.map(userFollow) ;

        // map user va follower
        if(userFollow.getUser() != null) {
            UserEntity userEntity = userMapper.fromDomainToEntity(userFollow.getUser());
            userFollowerEntity.setUser(userEntity);
        }

        if(userFollow.getFollower() != null) {
            UserEntity followerEntity = userMapper.fromDomainToEntity(userFollow.getFollower());
            userFollowerEntity.setFollower(followerEntity);
        }

        return userFollowerEntity ;
    }

    public UserFollow fromEntityToDomain(UserFollowerEntity userFollowerEntity) {
        if (userFollowerEntity == null) return null;
        if (fromEntityToDomainTypeMap == null) {
            fromEntityToDomainTypeMap = modelMapper.createTypeMap(UserFollowerEntity.class, UserFollow.class);
            fromEntityToDomainTypeMap.getMappings().clear();
            fromEntityToDomainTypeMap.addMappings(mapper -> {
                mapper.skip(UserFollow::setUser);
                mapper.skip(UserFollow::setFollower);
            });
            fromEntityToDomainTypeMap.implicitMappings();
        }

        UserFollow userFollow = fromEntityToDomainTypeMap.map(userFollowerEntity);

        // map user va follower
        if (userFollowerEntity.getUser() != null) {
            User user = userMapper.fromEntityToDomain(userFollowerEntity.getUser());
            userFollow.setUser(user);
        }

        if (userFollowerEntity.getFollower() != null) {
            User follower = userMapper.fromEntityToDomain(userFollowerEntity.getFollower());
            userFollow.setFollower(follower);
        }
        return userFollow ;
    }
}
