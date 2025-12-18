package com.spring.knowhub.infrastructure.mappers.user;

import com.spring.knowhub.domain.models.user.Permission;
import com.spring.knowhub.infrastructure.entities.user.PermissionEntity;
import com.spring.knowhub.infrastructure.exceptions.user.permission.PermissionMapperException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PermissionMapper {

    private final ModelMapper modelMapper ;
    private TypeMap<Permission , PermissionEntity> fromDomainToEntityTypeMap ;
    private TypeMap<PermissionEntity , Permission> fromEntityToDomainTypeMap ;

    public PermissionEntity fromDomainToEntity(Permission permission){
        try {
            if (fromDomainToEntityTypeMap == null) {
                fromDomainToEntityTypeMap = modelMapper.createTypeMap(Permission.class, PermissionEntity.class);
                fromDomainToEntityTypeMap.implicitMappings();
            }
            return fromDomainToEntityTypeMap.map(permission);

        } catch (Exception e) {
            throw PermissionMapperException.domainToEntityMappingFailed(e.getMessage());
        }
    }

    public Permission fromEntityToDomain(PermissionEntity permissionEntity){
        try {
            if (fromEntityToDomainTypeMap == null) {
                fromEntityToDomainTypeMap = modelMapper.createTypeMap(PermissionEntity.class, Permission.class);
                fromEntityToDomainTypeMap.implicitMappings();
            }
            return fromEntityToDomainTypeMap.map(permissionEntity);
        } catch (Exception e) {
            throw PermissionMapperException.entityToDomainMappingFailed(e.getMessage());
        }
    }
}
