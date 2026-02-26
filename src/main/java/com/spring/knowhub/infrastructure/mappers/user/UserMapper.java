package com.spring.knowhub.infrastructure.mappers.user;

import com.spring.knowhub.domain.models.user.Role;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.infrastructure.entities.user.RoleEntity;
import com.spring.knowhub.infrastructure.entities.user.UserEntity;
import com.spring.knowhub.infrastructure.exceptions.user.user.UserMapperException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;
    private final RoleMapper roleMapper;
    private TypeMap<User, UserEntity> fromDomainToEntityTypeMap;
    private TypeMap<UserEntity, User> fromEntityToDomainTypeMap;

    public UserEntity fromDomainToEntity(User user) {
        try {
            if (user == null) {
                throw UserMapperException.entityToDomainMappingFailed("Đối tượng truyền vào bị null");
            }

            if (fromDomainToEntityTypeMap == null) {
                fromDomainToEntityTypeMap = modelMapper.createTypeMap(User.class, UserEntity.class);
                fromDomainToEntityTypeMap.addMappings(mapper -> {
                    mapper.skip(UserEntity::setRole);
                });
                fromDomainToEntityTypeMap.implicitMappings();
            }

            UserEntity userEntity = fromDomainToEntityTypeMap.map(user);

            if (user.getRole() != null) {
                try {
                    Role role = user.getRole();
                    RoleEntity roleEntity = roleMapper.fromDomainToEntity(role);
                    userEntity.setRole(roleEntity);
                } catch (Exception ex) {
                    throw UserMapperException.entityToDomainMappingFailed("Lỗi mapping roles: " + ex.getMessage());
                }
            }

            return userEntity;
        } catch (UserMapperException ex) {
            throw ex;
        } catch (Exception e) {
            throw UserMapperException.domainToEntityMappingFailed(e.getMessage());
        }
    }

    public User fromEntityToDomain(UserEntity userEntity) {
        try {
            if (userEntity == null) {
                throw UserMapperException.entityToDomainMappingFailed("Đối tượng truyền vào bị null");
            }

            if (fromEntityToDomainTypeMap == null) {
                fromEntityToDomainTypeMap = modelMapper.createTypeMap(UserEntity.class, User.class);
                fromEntityToDomainTypeMap.addMappings(mapper -> {
                    mapper.skip(User::setRole);
                });
                fromEntityToDomainTypeMap.implicitMappings();
            }

            User user = modelMapper.map(userEntity, User.class);

            if (userEntity.getRole() != null) {
                try {
                    RoleEntity roleEntity = userEntity.getRole();
                    Role role = roleMapper.fromEntityToDomain(roleEntity);
                    user.setRole(role);
                } catch (Exception ex) {
                    throw UserMapperException.entityToDomainMappingFailed("Lỗi mapping roles: " + ex.getMessage());
                }
            }

            return user;
        } catch (UserMapperException ex) {
            throw ex;
        } catch (Exception e) {
            throw UserMapperException.entityToDomainMappingFailed(e.getMessage());
        }
    }
}
