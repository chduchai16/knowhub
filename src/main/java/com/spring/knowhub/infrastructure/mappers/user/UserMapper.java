package com.spring.knowhub.infrastructure.mappers.user;

import com.spring.knowhub.domain.models.user.Role;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.infrastructure.entities.user.RoleEntity;
import com.spring.knowhub.infrastructure.entities.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper ;
    private final RoleMapper roleMapper ;
    private TypeMap<User , UserEntity> fromDomainToEntityTypeMap ;
    private TypeMap<UserEntity , User> fromEntityToDomainTypeMap ;

    public UserEntity fromDomainToEntity(User user) {
        if (fromDomainToEntityTypeMap == null) {
            fromDomainToEntityTypeMap = modelMapper.createTypeMap(User.class, UserEntity.class);
            fromDomainToEntityTypeMap.addMappings(mapper -> {
                mapper.skip(UserEntity::setRoles);
            }) ;
            fromDomainToEntityTypeMap.implicitMappings();
        }

        UserEntity userEntity = fromDomainToEntityTypeMap.map(user) ;

        // map roles
        if(user.getRoles() != null && !user.getRoles().isEmpty()) {
            Set<RoleEntity> roles = user.getRoles().stream()
                    .map(roleMapper::fromDomainToEntity)
                    .collect(java.util.stream.Collectors.toSet()) ;
            userEntity.setRoles(roles) ;
        }

        return userEntity ;
    }

    public User fromEntityToDomain(UserEntity userEntity) {
        if (fromEntityToDomainTypeMap == null) {
            fromEntityToDomainTypeMap = modelMapper.createTypeMap(UserEntity.class, User.class);
            fromEntityToDomainTypeMap.addMappings(mapper -> {
                mapper.skip(User::setRoles);
            }) ;
            fromEntityToDomainTypeMap.implicitMappings();
        }

        User user = fromEntityToDomainTypeMap.map(userEntity) ;

        // map roles
        if(userEntity.getRoles() != null && !userEntity.getRoles().isEmpty()) {
            Set<Role> roles = userEntity.getRoles().stream()
                    .map(roleMapper::fromEntityToDomain)
                    .collect(java.util.stream.Collectors.toSet()) ;
            user.setRoles(roles) ;
        }

        return user ;
    }
}
