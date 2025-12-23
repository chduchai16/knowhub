package com.spring.knowhub.presentation.mappers.user;

import com.spring.knowhub.domain.exceptions.user.role.RoleNotFoundException;
import com.spring.knowhub.domain.models.user.Permission;
import com.spring.knowhub.domain.models.user.Role;
import com.spring.knowhub.infrastructure.configurations.ModelMapperConfiguration;
import com.spring.knowhub.infrastructure.exceptions.user.role.RoleMapperException;
import com.spring.knowhub.presentation.exceptions.user.RoleResponseMappingException;
import com.spring.knowhub.presentation.response.user.RoleResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleResponseMapper {

    private final ModelMapperConfiguration modelMapper ;
    private TypeMap<Role , RoleResponse> fromDomainToResponseTypeMap ;

    public RoleResponse fromDomainToResponse(Role role) {
        if(role == null) {
            throw RoleResponseMappingException.objectNull();
        }
        try {
            if(fromDomainToResponseTypeMap == null) {
                fromDomainToResponseTypeMap = modelMapper.modelMapper().createTypeMap(Role.class, RoleResponse.class);
                fromDomainToResponseTypeMap.addMappings(mapper -> {
                    mapper.skip(RoleResponse::setPermissionNames);
                }) ;
                fromDomainToResponseTypeMap.implicitMappings();
            }

            RoleResponse response = fromDomainToResponseTypeMap.map(role);
            if(role.getPermissions() != null) {
                response.setPermissionNames(
                        role.getPermissions()
                                .stream()
                                .map(Permission::getCode)
                                .collect(java.util.stream.Collectors.toSet())
                );
            }
            return response ;
        } catch (Exception e) {
            throw RoleResponseMappingException.mappingError(e);
        }
    }
}
