package com.spring.knowhub.infrastructure.mappers.user;

import com.spring.knowhub.domain.models.user.Role;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.infrastructure.entities.user.RoleEntity;
import com.spring.knowhub.infrastructure.entities.user.UserEntity;
import com.spring.knowhub.infrastructure.exceptions.user.UserMapperException;
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
        try {
            // Validate user field
            if (user == null) {
                throw UserMapperException.invalidFieldMapping("user", "user không được phép null");
            }

            if (fromDomainToEntityTypeMap == null) {
                fromDomainToEntityTypeMap = modelMapper.createTypeMap(User.class, UserEntity.class);
                fromDomainToEntityTypeMap.addMappings(mapper -> {
                    mapper.skip(UserEntity::setRoles);
                }) ;
                fromDomainToEntityTypeMap.implicitMappings();
            }

            UserEntity userEntity = fromDomainToEntityTypeMap.map(user) ;

            // map roles - nếu roles tồn tại thì map, không thì bỏ qua
            if(user.getRoles() != null && !user.getRoles().isEmpty()) {
                try {
                    Set<RoleEntity> roles = user.getRoles().stream()
                            .map(roleMapper::fromDomainToEntity)
                            .collect(java.util.stream.Collectors.toSet()) ;
                    userEntity.setRoles(roles) ;
                } catch (Exception ex) {
                    throw UserMapperException.nullRoleMapping();
                }
            }

            return userEntity ;
        } catch (UserMapperException ex) {
            throw ex;  // Re-throw UserMapperException đã được throw
        } catch (Exception e) {
            throw UserMapperException.domainToEntityMappingFailed(e.getMessage());
        }
    }

    public User fromEntityToDomain(UserEntity userEntity) {
        try {
            // Validate userEntity field
            if (userEntity == null) {
                throw UserMapperException.invalidFieldMapping("userEntity", "userEntity không được phép null");
            }

            if (fromEntityToDomainTypeMap == null) {
                fromEntityToDomainTypeMap = modelMapper.createTypeMap(UserEntity.class, User.class);
                fromEntityToDomainTypeMap.addMappings(mapper -> {
                    mapper.skip(User::setRoles);
                }) ;
                fromEntityToDomainTypeMap.implicitMappings();
            }

            User user = fromEntityToDomainTypeMap.map(userEntity) ;

            // map roles - nếu roles tồn tại thì map, không thì bỏ qua
            if(userEntity.getRoles() != null && !userEntity.getRoles().isEmpty()) {
                try {
                    Set<Role> roles = userEntity.getRoles().stream()
                            .map(roleMapper::fromEntityToDomain)
                            .collect(java.util.stream.Collectors.toSet()) ;
                    user.setRoles(roles) ;
                } catch (Exception ex) {
                    throw UserMapperException.nullRoleMapping();
                }
            }

            return user ;
        } catch (UserMapperException ex) {
            throw ex;  // Re-throw UserMapperException đã được throw
        } catch (Exception e) {
            throw UserMapperException.entityToDomainMappingFailed(e.getMessage());
        }
    }
}
