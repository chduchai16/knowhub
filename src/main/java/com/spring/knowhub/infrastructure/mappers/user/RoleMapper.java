package com.spring.knowhub.infrastructure.mappers.user;

import com.spring.knowhub.domain.models.user.Permission;
import com.spring.knowhub.domain.models.user.Role;
import com.spring.knowhub.infrastructure.entities.user.RoleEntity;
import com.spring.knowhub.infrastructure.exceptions.user.role.RoleMapperException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class RoleMapper {

    private final ModelMapper modelMapper;
    private final PermissionMapper permissionMapper;
    private TypeMap<Role, RoleEntity> fromDomainToEntityTypeMap;
    private TypeMap<RoleEntity, Role> fromEntityToDomainTypeMap;

    public Role fromEntityToDomain(RoleEntity roleEntity) {
        try {
            if (roleEntity == null) {
                throw RoleMapperException.entityToDomainMappingFailed("Đối tượng truyền vào bị null");
            }
            if (fromEntityToDomainTypeMap == null) {
                fromEntityToDomainTypeMap = modelMapper.createTypeMap(RoleEntity.class, Role.class);
                fromEntityToDomainTypeMap.getMappings().clear();
                fromEntityToDomainTypeMap.addMappings(mapper -> {
                    mapper.skip(Role::setPermissions);
                });
                fromEntityToDomainTypeMap.implicitMappings();
            }

            Role role = modelMapper.map(roleEntity, Role.class);

            // map permissions
            if (roleEntity.getPermissions() != null && !roleEntity.getPermissions().isEmpty()) {
                Set<Permission> permissions = roleEntity.getPermissions()
                        .stream()
                        .map(permissionMapper::fromEntityToDomain)
                        .collect(java.util.stream.Collectors.toSet());
                role.setPermissions(permissions);
            }

            return role;
        } catch (Exception ex) {
            throw RoleMapperException.entityToDomainMappingFailed(ex.getMessage());
        }

    }

    public RoleEntity fromDomainToEntity(Role role) {
        try {
            if (role == null) {
                throw RoleMapperException.domainToEntityMappingFailed("Đối tượng truyền vào bị null");
            }
            if (fromDomainToEntityTypeMap == null) {
                fromDomainToEntityTypeMap = modelMapper.createTypeMap(Role.class, RoleEntity.class);
                fromDomainToEntityTypeMap.getMappings().clear();
                fromDomainToEntityTypeMap.addMappings(mapper -> {
                    mapper.skip(RoleEntity::setPermissions);
                });
                fromDomainToEntityTypeMap.implicitMappings();
            }

            RoleEntity roleEntity = fromDomainToEntityTypeMap.map(role);

            // map permissions
            if (role.getPermissions() != null && !role.getPermissions().isEmpty()) {
                Set<com.spring.knowhub.infrastructure.entities.user.PermissionEntity> permissionEntities = role
                        .getPermissions()
                        .stream()
                        .map(permissionMapper::fromDomainToEntity)
                        .collect(java.util.stream.Collectors.toSet());
                roleEntity.setPermissions(permissionEntities);
            }

            return roleEntity;

        } catch (Exception e) {
            throw RoleMapperException.domainToEntityMappingFailed(e.getMessage());
        }
    }
}
