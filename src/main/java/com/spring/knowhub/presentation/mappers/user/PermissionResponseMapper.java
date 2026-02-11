package com.spring.knowhub.presentation.mappers.user;

import com.spring.knowhub.domain.models.user.Permission;
import com.spring.knowhub.infrastructure.configurations.ModelMapperConfiguration;
import com.spring.knowhub.presentation.response.user.PermissionResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PermissionResponseMapper {
    private final ModelMapperConfiguration modelMapper;
    private TypeMap<Permission, PermissionResponse> fromDomainToResponseMap;

    public PermissionResponse fromDomainToResponse(Permission permission) {
        if (permission == null) {
            return null;
        }

        if (fromDomainToResponseMap == null) {
            fromDomainToResponseMap = modelMapper.modelMapper().createTypeMap(Permission.class,
                    PermissionResponse.class);
            fromDomainToResponseMap.implicitMappings();
        }

        return fromDomainToResponseMap.map(permission);
    }
}
